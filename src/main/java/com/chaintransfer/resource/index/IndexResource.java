package com.chaintransfer.resource.index;

import com.chaintransfer.grpc.PaymentResponse;
import com.chaintransfer.grpc.PaymentStatus;
import com.chaintransfer.service.PaymentService;
import com.chaintransfer.service.UploadService;
import io.quarkus.qute.CheckedTemplate;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.reactive.RestForm;

import java.util.UUID;

@Path("/")
@ApplicationScoped
public class IndexResource {

    private final UploadService uploadService;
    private final PaymentService paymentService;

    public IndexResource(
            UploadService uploadService,
            PaymentService paymentService
    ) {
        this.uploadService = uploadService;
        this.paymentService = paymentService;
    }


    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance index();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return Templates.index();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    @Path("/upload")
    public String upload(@RestForm("file") FileUpload file) {
        boolean result = uploadService.send(file);
        return result ? "<p class='text-green-400'>✅ File sent.</p>"
                : "<p class='text-red-400'>❌ Failed.</p>";
    }

    @POST
    @Path("/payment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public String payment() {
        PaymentResponse response = paymentService.processPayment(
                "00000000-0000-0000-0000-000000000000",
                10L,
                "EUR",
                UUID.randomUUID().toString()
        );

        return response.getStatus() == PaymentStatus.SUCCESS
                ? "<p class='text-green-400'>✅ Limit increased!</p>"
                : "<p class='text-red-400'>❌ Payment failed: " + response.getMessage() + "</p>";
    }

}
