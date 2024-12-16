package servlets;

import DAO.DetalleVentaDAO;
import DAO.VentaDAO;
import Logica.DetalleVenta;
import Logica.Venta;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/generarPDF")
public class generarPDF extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=registro_compras.pdf");

        // Inicializa los objetos necesarios
        String id = request.getParameter("id");
        DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();
        List<DetalleVenta> detalles = detalleVentaDAO.obtenerDetalleVentaPorId(id);  // Obtener detalles de venta

        StringWriter writer = new StringWriter();
        writer.append("<html><head><style>");
        writer.append("table { width: 100%; border-collapse: collapse; }");
        writer.append("th, td { border: 1px solid black; padding: 5px; text-align: left; }");
        writer.append("h1 { text-align: center; }");  // Centrar el título
        writer.append("p { text-align: center; }");
        writer.append("</style></head><body>");

        // Agregar el título adicional y centrarlo
        writer.append("<h1>OJITOS DE WEVO</h1>");
        writer.append("<p>------------------------------------</p>");
        writer.append("<h1>GRACIAS POR REALIZAR SU COMPRA CON NOSOTROS</h1>");
        // Título principal
        writer.append("<h2>Registro de Compras</h2>");
        writer.append("<table>");
        writer.append("<tr><th>ID</th><th>Fecha</th><th>Compra</th><th>Cantidad</th><th>Precio Unitario</th><th>Total</th><th>Método de Pago</th><th>Estado</th></tr>");

        // Convertir la fecha a String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (DetalleVenta detallex : detalles) {
            VentaDAO ventaDAO = new VentaDAO();
            List<Venta> ventas = ventaDAO.obtenerVentaPorIdVenta(detallex.getIdVenta());
            for (Venta venta : ventas) {
                writer.append("<tr>");
                writer.append("<td>").append(String.valueOf(detallex.getIdVenta())).append("</td>");
                writer.append("<td>").append(sdf.format(venta.getFecha())).append("</td>");
                writer.append("<td>").append(detallex.getVentas()).append("</td>");
                writer.append("<td>").append(String.valueOf(detallex.getCantidad())).append("</td>");
                writer.append("<td>").append(String.valueOf(detallex.getPrecioUnitario())).append("</td>");
                writer.append("<td>").append(String.valueOf(detallex.getSubtotal())).append("</td>");
                writer.append("<td>").append(venta.getMetodoPago()).append("</td>");
                writer.append("<td>").append(detallex.getEstado()).append("</td>");
                writer.append("</tr>");
            }
        }

        writer.append("</table>");
        writer.append("</body></html>");

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(writer.toString());
        renderer.layout();
        try {
            renderer.createPDF(response.getOutputStream());
        } catch (DocumentException ex) {
            Logger.getLogger(generarPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

