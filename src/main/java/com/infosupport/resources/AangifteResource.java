package com.infosupport.resources;

import com.infosupport.dao.AbstractDao;
import com.infosupport.domein.Aangifte;
import com.infosupport.util.NotSecured;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Dependent
public class AangifteResource {

    private int id;

    @Inject
    private AbstractDao<Aangifte> abstractDao;

    @NotSecured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Aangifte getAangifte(@Context UriInfo uriInfo) {
        abstractDao.get(id).set_self(uriInfo.getAbsolutePath().toString());
        return abstractDao.get(id);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAangifte() {
        if (id < 1) throw new BadRequestException("mag geen 0 zijn");
        abstractDao.delete(id);
    }

    public AangifteResource with(int id) {
        this.id = id;
        return this;
    }
}
