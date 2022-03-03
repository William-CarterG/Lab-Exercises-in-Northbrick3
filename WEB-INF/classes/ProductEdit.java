import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class ProductEdit extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.println(Utils.header("Product Form"));
        toClient.println("<form action='ProductUpdate' method='GET'>");
        toClient.println("<table border='1'>");
        String idStr = req.getParameter("id");
        ProductData product = ProductData.getProduct(connection, idStr);
        toClient.println("<tr><td>Id</td>");
        toClient.println("<td><input name='productId' value='" + product.productId + "'></td></tr>");
        toClient.println("<tr><td>Name</td>");
        String name = product.productName;
        System.out.println("Name: " + name);
        name = name.replace("'","&#39;");
        System.out.println("Name: " + name);
        toClient.println("<td><input name='productName' value='" + name + "'></td></tr>");
        toClient.println("<tr><td>Supplier</td>");
        toClient.println("<td><input name='supplierId' value='" + product.supplierId + "'></td>");
        toClient.println("<tr><td>Price</td>");
        toClient.println("<td><input name='unitPrice' value='" + product.unitPrice + "'></td>");
        toClient.println("</tr>");
        toClient.println("<tr><td>Image</td>");
        toClient.println("<td><img src='http://northbrick1.appspot.com/images/" + product.productId + ".png'></td>");
        toClient.println("</tr>");

        ProductData productEdit = ProductData.getProductEdit(connection, idStr);
        toClient.println("<tr><td>Category Id</td>");
        toClient.println("<td><input name='CategoryId' value='" + productEdit.CategoryId + "'></td></tr>");
        toClient.println("<tr><td>Quantity Per Unit</td>");
        toClient.println("<td><input name='QuantityPerUnit' value='" + productEdit.QuantityPerUnit + "'></td></tr>");
        toClient.println("<tr><td>Units In Stock</td>");
        toClient.println("<td><input name='sUnitsInStock' value='" + product.UnitsInStock + "'></td>");
        toClient.println("<tr><td>Units On Order</td>");
        toClient.println("<td><input name='UnitsOnOrder' value='" + product.UnitsOnOrder + "'></td>");
        toClient.println("</tr>");
        toClient.println("</table>");
        toClient.println("<input type='submit'>");
        toClient.println("</form>");
        toClient.println(Utils.footer("Product Form"));
        toClient.close();
    }
}