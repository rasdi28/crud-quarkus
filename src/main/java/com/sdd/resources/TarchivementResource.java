package com.sdd.resources;

import com.sdd.domain.Tarchivement;
import com.sdd.model.ObjectResp;
import com.sdd.repo.TarchivementRepo;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/archivement")
public class TarchivementResource {

    @Inject
    TarchivementRepo repo;


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public ObjectResp getAll(){
        ObjectResp objResp = new ObjectResp();
        try {
            objResp.setCode(Response.Status.OK.getStatusCode());
            objResp.setMessage(Response.Status.OK.getReasonPhrase());
            objResp.setData(repo.listAll());
        }catch (Exception e){
            e.printStackTrace();
            objResp.setCode(Response.Status.BAD_REQUEST.getStatusCode());
            objResp.setMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        }
        return objResp;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getByUser/{muserfk}")
    public ObjectResp getByUser(@PathParam("muserfk") Long muserpk){
        ObjectResp objResp = new ObjectResp();
        try {

            objResp.setCode(Response.Status.OK.getStatusCode());
            objResp.setMessage(Response.Status.OK.getReasonPhrase());
            objResp.setData(repo.list("muserfk",Sort.by("title desc"), muserpk));

        }catch (Exception e){
            e.printStackTrace();
            objResp.setCode(Response.Status.BAD_REQUEST.getStatusCode());
            objResp.setMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        }

        return objResp;
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectResp create(Tarchivement obj){
        ObjectResp objResp = new ObjectResp();
        try {
            obj.setTarchivementpk(null);
            repo.persist(obj);
            objResp.setCode(Response.Status.OK.getStatusCode());
            objResp.setMessage(Response.Status.OK.getReasonPhrase());
            objResp.setData(obj);

        }catch (Exception e){
            e.printStackTrace();
            objResp.setCode(Response.Status.BAD_REQUEST.getStatusCode());
            objResp.setMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        }

        return objResp;
    }



}
