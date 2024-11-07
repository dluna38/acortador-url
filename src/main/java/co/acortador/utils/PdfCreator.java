/*
package co.acortador.utils;

import co.edu.iudigital.parqueadero.exceptions.UnknownException;
import co.edu.iudigital.parqueadero.models.Pago;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class PdfCreator {
    private static final String URL_DIRECTORY_PDF= "src/main/resources/pdf/";
    private static final String URL_DIRECTORY_PDF_TARGET= "src/main/resources/pdf/target";
    private static final String FORM_PDF= "form_factura.pdf";


    public static byte[] generarPdfPago(Pago pago){
        try(PDDocument pdfDocument = Loader.loadPDF(new File(URL_DIRECTORY_PDF+FORM_PDF))) {
            PDAcroForm pdfAcroForm = pdfDocument.getDocumentCatalog().getAcroForm();
            //DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")
            if(pdfAcroForm == null){
                throw new UnknownException();
            }

            // Retrieve an individual field and set its value.
            pdfAcroForm.getField( "estanciaNumero").setValue(pago.getEstancia().getId().toString());
            pdfAcroForm.getField("fechaEntrada").setValue(UtilString.formatDate(pago.getEstancia().getFechaEntrada()));
            pdfAcroForm.getField("fechaSalida").setValue(UtilString.formatDate(pago.getEstancia().getFechaSalida()));
            pdfAcroForm.getField("minutos").setValue(pago.getEstancia().getMinutosTotales().toString()+" m");
            pdfAcroForm.getField("placa").setValue(pago.getEstancia().getVehiculo().getPlaca());
            pdfAcroForm.getField("dueno").setValue(pago.getEstancia().getVehiculo().getDueno().getNombre());
            pdfAcroForm.getField("identificacion").setValue(pago.getEstancia().getVehiculo().getDueno().getDocumentoIdentificacion());
            pdfAcroForm.getField("tarifa").setValue(UtilString.formatCurrencyNumberToCO(pago.getTarifa().getTarifaValor())+" / "+pago.getTarifa().getPorMinutos()+" m");
            pdfAcroForm.getField("metodoPago").setValue(pago.getMetodoPago());
            pdfAcroForm.getField("cobro").setValue(UtilString.formatCurrencyNumberToCO(pago.getCobroTotal()));
            //pdfDocument.save("src/main/resources/target/FillFormField.pdf");

            pdfAcroForm.flatten();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            pdfDocument.save(baos);
            return baos.toByteArray();

        } catch (IOException | UnknownException e) {
            throw new UnknownException("No se pudo crear el archivo",e.getMessage());
        }
    }
}
*/
