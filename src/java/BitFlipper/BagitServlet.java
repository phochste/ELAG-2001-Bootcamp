/*
 *  Copyright (c) 2011 . Patrick Hochstenbach <Patrick.Hochstenbach@gmail.com>
 */

package BitFlipper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author hochsten
 */
public class BagitServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart) {
                String fileName = "";
                String mimeType = "unknown/unknown";
                byte[] data = null;

                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List /* FileItem */ items = upload.parseRequest(request);
                Iterator iterator = items.iterator();

                while(iterator.hasNext()) {
                    FileItem fileItem = (FileItem) iterator.next();

                    if (fileItem.isFormField()) {
                    }
                    else {
                        fileName = fileItem.getName();
                        mimeType = fileItem.getContentType();
                        data = fileItem.get();
                    }
                }


                store_upload(fileName,data);

                System.err.println("uploaded: " + data.length + " bytes");
                System.err.println("mime-type: " + mimeType);

                response.sendRedirect("uploads");
            }
            else {
                response.setContentType("text/html;charset=UTF-8");

                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>BitFlipper</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>You need to upload a file</h1>");
                out.println("</body>");
                out.println("</html>");
                out.close();
            }
        }
        catch (Exception e) {
        }
    }

    protected void store_upload(String fileName, byte[] data) throws Exception {
       String uploadDir = getServletContext().getRealPath("/uploads");


       ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(data));
       ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(new File(uploadDir,fileName)));

       String bagName = null;

       ZipEntry entry = null;
       while ( (entry = zin.getNextEntry()) != null) {

           if (bagName == null && entry.isDirectory()) {
            bagName = entry.getName();
           }

           zout.putNextEntry(entry);

           byte[] buffer = new byte[64*1024];
           int length;

           //read the entry from zip file and extract it to disk
           while( (length = zin.read(buffer)) > 0 ) {
              zout.write(buffer, 0, length);
           }

           zout.closeEntry();
       }

       // Now add some junk...
       Random generator = new Random((new Date()).getTime());
       int rand = generator.nextInt(4);

       switch (rand) {
           case 0:
               addFile(zout, bagName + "/data/" + "Thumbs.db", "");
               break;
           case 1:
               addFile(zout, bagName + "/data/" + "README.txt", "Ignore this text");
               break;
           case 2:
               addFile(zout, bagName + "manifest-md5.txt", "");
               break;
           default:
               break;
       }

       zin.close();
       zout.close();
    }

    void addFile(ZipOutputStream zout, String fileName, String content) throws Exception {
       zout.putNextEntry(new ZipEntry(fileName));
       zout.write(content.getBytes());
       zout.closeEntry();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
