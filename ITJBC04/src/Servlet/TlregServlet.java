//package Servlet;
//
//
//
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//
//import com.oreilly.servlet.multipart.FilePart;
//import com.oreilly.servlet.multipart.MultipartParser;
//import com.oreilly.servlet.multipart.ParamPart;
//import com.oreilly.servlet.multipart.Part;
//
//import Bean.tlregbean;
//import Imple.Imple;
//import Inter.Inter;
//
///**
// * Servlet implementation class TlregServlet
// */
//@WebServlet("/TlregServlet")
//public class TlregServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public TlregServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//
//		String filetype=null;
//		Part part;
//		FilePart filepart;
//		long size=0;
//		String filename=null;
//
//		filename=request.getParameter("filename");
//		System.out.println("filename:"+filename);
//
//		MultipartParser mp = new MultipartParser(request, 999999999);
//
//
//		PrintWriter out=response.getWriter();
//
//		ArrayList list=new ArrayList<>();
//
//
//
//		String path = getServletContext().getRealPath(" ");
//		System.out.println(path);
//
//
//		String editPath = path.substring(0, path.indexOf("."));
//		System.out.println(editPath);
//
//		String fullpath = editPath + "ITJBC04\\WebContent\\Local\\";
//		System.out.println(fullpath);
//
//		while ((part = mp.readNextPart()) != null)
//		{
//			if (part.isFile()) {
//				filepart = (FilePart) part;
//
//				filetype = filepart.getContentType();
//
//				 filename = filepart.getFileName();
//				System.out.println(filename);
//
//				fullpath = fullpath + filename;
//				System.out.println(fullpath);
//
//				File file = new File(fullpath);
//				size = filepart.writeTo(file);
//
//				BufferedReader br = new BufferedReader(new FileReader(file));
//
//
//			}
//			else if(part.isParam())
//			{
//				ParamPart param = (ParamPart) part;
//				String name = param.getName();
//
//				String value = param.getStringValue();
//
//				System.out.println("Loadinggg...... " + value);
//				list.add(value);
//			}
//		}
//		tlregbean tr=new tlregbean();
//
//
//				tr.setTeam((list.get(0).toString()));
//				System.out.println("Team :"+list.get(0).toString());
//
//	 		 tr.setName((list.get(1).toString()));
//		 	    System.out.println("Name :"+list.get(1).toString());
//
//		 	   tr.setEmail((list.get(2).toString()));
//		 	    System.out.println("Email :"+list.get(2).toString());
//
//		 	   tr.setMobile((list.get(3).toString()));
//		 	    System.out.println("Mobile :"+list.get(3).toString());
//
//		 	   tr.setPassword((list.get(4).toString()));
//		 	    System.out.println("Password :"+list.get(4).toString());
//
//		 	   tr.setCpassword((list.get(5).toString()));
//		 	    System.out.println("Confirm Password :"+list.get(5).toString());
//
//
//	 	   tr.setImage(filename);
//
//
//		Inter in=new Imple();
//		int i=in.tlreg(tr);
//		if(i==1){
//			response.sendRedirect("managementhome.jsp");
//		}
//		else{
//			response.sendRedirect("error.jsp");
//		}
//
//
//	}
//
//	}
//
//
package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import Bean.tlregbean;
import Imple.Imple;
import Inter.Inter;

/**
 * Servlet implementation class TlregServlet
 */
@WebServlet("/TlregServlet")
public class TlregServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TlregServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filetype = null;
        Part part;
        FilePart filepart;
        long size = 0;
        String filename = null;

        // Removed specific size limit for stability, using generous default
        MultipartParser mp = new MultipartParser(request, 999999999);

        PrintWriter out = response.getWriter();

        // FIX 1: Defined the list as <String> to fix the "Unchecked" warning
        ArrayList<String> list = new ArrayList<>();

        // FIX 2: Better Path Logic for IntelliJ/Tomcat 9
        // This gets the folder where the website is currently running
        String path = getServletContext().getRealPath("/");

        // We ensure we save into a folder named "Local" inside the web app
        String fullpath = path + "Local" + File.separator;

        System.out.println("Saving file to: " + fullpath);

        // Create the directory if it doesn't exist
        File saveDir = new File(fullpath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        while ((part = mp.readNextPart()) != null) {
            if (part.isFile()) {
                filepart = (FilePart) part;
                filetype = filepart.getContentType();
                filename = filepart.getFileName();

                if (filename != null) { // Only write if a file was actually uploaded
                    System.out.println("Uploading: " + filename);
                    // Save to the safe path we created above
                    File file = new File(fullpath + filename);
                    size = filepart.writeTo(file);
                }
            }
            else if (part.isParam()) {
                ParamPart param = (ParamPart) part;
                String value = param.getStringValue();
                System.out.println("Loading param: " + value);
                list.add(value);
            }
        }

        // Logic to prevent crash if list is empty
        if(list.size() >= 6) {
            tlregbean tr = new tlregbean();

            // FIX 3: No need for .toString() anymore because list is already <String>
            tr.setTeam(list.get(0));
            System.out.println("Team :" + list.get(0));

            tr.setName(list.get(1));
            System.out.println("Name :" + list.get(1));

            tr.setEmail(list.get(2));
            System.out.println("Email :" + list.get(2));

            tr.setMobile(list.get(3));
            System.out.println("Mobile :" + list.get(3));

            tr.setPassword(list.get(4));
            System.out.println("Password :" + list.get(4));

            tr.setCpassword(list.get(5));
            System.out.println("Confirm Password :" + list.get(5));

            tr.setImage(filename);

            Inter in = new Imple();
            int i = in.tlreg(tr);

            if (i == 1) {
                response.sendRedirect("managementhome.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
            System.out.println("Error: Not enough parameters received.");
            response.sendRedirect("error.jsp");
        }
    }
}