/*
 *  Copyright (c) 2011 . Patrick Hochstenbach <Patrick.Hochstenbach@gmail.com>
 */

package BitFlipper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
public class FlipServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int count = 0;
        
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
                        String name  = fileItem.getFieldName();
                        String value = fileItem.getString();

                        if ("count".equals(name)) {
                            count = Integer.parseInt(value);
                        }
                    }
                    else {
                        fileName = fileItem.getName();
                        mimeType = fileItem.getContentType();
                        data = fileItem.get();
                    }
                }

                data = randomError(data, count);

                System.err.println("uploaded: " + data.length + " bytes");
                System.err.println("mime-type: " + mimeType);
                
                response.setContentType(mimeType);
                response.setHeader("Content-disposition","attachment; filename=" + fileName);

                OutputStream out = response.getOutputStream();
                out.write(data);
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

    protected byte[] randomError(byte[] data, int count) {
        Random r = new Random();
        r.setSeed( (new Date()).getTime() );

        int num_bits = 8 * data.length ;

        for (int i = 0; i < count; i++) {
            int rand_bit  = r.nextInt(num_bits);
            int rand_pos  = rand_bit / 8;
            int rand_bit_pos = rand_bit % 8;
            System.err.println("flipping bit: " + rand_bit + " " + rand_pos + "/" + rand_bit_pos);
            data[rand_pos] = (byte) (data[rand_pos] ^ (1 << rand_bit_pos));
        }

        return data;
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
