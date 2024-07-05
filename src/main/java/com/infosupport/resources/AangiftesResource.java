package com.infosupport.resources;

import com.infosupport.dao.AangifteDAO;
import com.infosupport.domein.Aangifte;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import java.util.List;

@Path("aangiftes")
public class AangiftesResource {

    @Inject
    private AangifteDAO aangifteDAO;

    @Inject
    private AangifteResource aangifteResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Alle aangiftes ophalen")
    public List<Aangifte> getAll(@Context UriInfo uriInfo) {
        List<Aangifte> aangiftes = aangifteDAO.getAangiftes();
        aangiftes.forEach(aangifte -> aangifte.set_self(uriInfo.getAbsolutePath().toString() + "/" + aangifte.getId()));
        return aangiftes;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aangifte postAangifte(@Context UriInfo uriInfo, Aangifte aangifte) {
        Aangifte aangifte1 = aangifteDAO.add(aangifte);
        aangifte1.set_self(uriInfo.getAbsolutePath().toString() + "/" + aangifte.getId());
        return aangifte1;

    }

    @Path("{id}")
    @Operation(description = "aangifte opvragen/verwijderen")
    public AangifteResource verwerkAangifte(@Parameter(description = "ID van de aangifte") @PathParam("id") int id) {
        return aangifteResource.with(id);
    }
}
