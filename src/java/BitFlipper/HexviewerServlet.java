/*
 *  Copyright (c) 2011 . Patrick Hochstenbach <Patrick.Hochstenbach@gmail.com>
 */

package BitFlipper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
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
public class HexviewerServlet extends HttpServlet {
    static final String HEXES = "0123456789ABCDEF";
    
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

                System.err.println("uploaded: " + data.length + " bytes");
                System.err.println("mime-type: " + mimeType);

                response.setContentType("text/html");

                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>HexViewer</title>");
                out.println("<style type=\"text/css\">body { font-family: helvetica; |</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + fileName + "</h1>");

                String hex = getHex(data);

                out.print("<tt>");
                for (int i = 0 ; i < hex.length() ; i += 100) {
                    out.print(hex.substring(i,i+100) + "<br/>");
                }
                out.print("</tt>");
                
                out.println("</body>");
                out.println("</html>");
                out.close();
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

    public static String getHex( byte [] raw ) {
        if ( raw == null ) {
            return null;
        }

        final StringBuilder hex = new StringBuilder( 2 * raw.length );
        for (int i = 0 ; i < raw.length ; i++ ) {
            hex.append(HEXES.charAt((raw[i] & 0xF0) >> 4))
                .append(HEXES.charAt((raw[i] & 0x0F)));
        }
        return hex.toString();
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
