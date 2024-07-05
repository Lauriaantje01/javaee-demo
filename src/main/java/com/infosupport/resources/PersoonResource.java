package com.infosupport.resources;

import com.infosupport.domein.Persoon;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("personen")
public class PersoonResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Persoon getPersoon() {
        return new Persoon("Jan");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Persoon addPersoon() {
        return new Persoon("Jan");
    }
}
