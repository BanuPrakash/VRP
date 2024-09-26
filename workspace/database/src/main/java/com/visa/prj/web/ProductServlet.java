package com.visa.prj.web;

import com.visa.prj.dao.DaoException;
import com.visa.prj.dao.ProductDao;
import com.visa.prj.dao.ProductDaoJdbcImpl;
import com.visa.prj.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    // <a href="products">Get All Products</a> <br />
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html"); // MIME type
//        PrintWriter out = resp.getWriter(); // open character stream to browser / client
//        out.print("<html><body>");
//        out.print("<table border=\"1\">");
//        out.print("<tr>");
//            out.print("<th>ID</th>");
//            out.print("<th>Name</th>");
//            out.print("<th>Price</th>");
//        out.print("</tr>");
//
//        ProductDao productDao = new ProductDaoJdbcImpl();
//        List<Product> products = productDao.getProducts();
//        for(Product p : products) {
//            out.print("<tr>");
//                out.print("<td>" + p.getId() + "</td>");
//                out.print("<td>" + p.getName() + "</td>");
//                out.print("<td>" + p.getPrice() + "</td>");
//            out.print("</tr>");
//        }
//        out.print("</table>");
//        out.print("<a href='index.jsp'>Back</a>");
//        out.print("</body></html>");
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDaoJdbcImpl();
        List<Product> products = productDao.getProducts();
        req.setAttribute("products", products);
     req.getRequestDispatcher("list.jsp").forward(req, resp); // server side redirection
//        resp.sendRedirect("list.jsp"); // client side redirection
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product  p = Product.builder()
                .name(req.getParameter("name"))
                .price(Double.parseDouble(req.getParameter("price")))
                .quantity(Integer.parseInt(req.getParameter("qty")))
                        .build();
        // use factory
        ProductDao productDao = new ProductDaoJdbcImpl();
        try {
            productDao.addProduct(p);
            // client side forwarding
            resp.sendRedirect("index.jsp?msg=Product added successfully!!!");
        } catch (DaoException ex) {
            resp.sendRedirect("error.jsp?msg=" + ex.getMessage());
        }
    }
}
