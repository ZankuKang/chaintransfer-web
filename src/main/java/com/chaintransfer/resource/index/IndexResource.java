package com.chaintransfer.resource.index;

import com.chaintransfer.service.UploadService;
import io.quarkus.qute.CheckedTemplate;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.reactive.RestForm;

@Path("/")
@ApplicationScoped
public class IndexResource {

    private final UploadService uploadService;

    public IndexResource(UploadService uploadService) {
        this.uploadService = uploadService;
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

}
