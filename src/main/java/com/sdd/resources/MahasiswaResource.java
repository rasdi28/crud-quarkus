package com.sdd.resources;

import com.sdd.domain.Mmahasiswa;
import com.sdd.model.ObjectResp;
import com.sdd.repo.MmahasiswaRepo;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.annotations.Param;

import javax.inject.Inject;
import javax.transaction.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/mahasiswa")
public class MahasiswaResource {

    @ConfigProperty(name = "label.msg.saved")
    String lblmsg_save;

    @ConfigProperty(name = "label.msg.id.notfound")
    String lblmsg_empty;


    @Inject
    MmahasiswaRepo repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectResp listAll(){
        ObjectResp objResp = new ObjectResp();
        try {
            objResp.setCode(Response.Status.OK.getStatusCode());
            objResp.setMessage(Response.Status.OK.getReasonPhrase());
            objResp.setData(repo.listAll());
        }catch (Exception e){
            objResp.setCode(Response.Status.NOT_FOUND.getStatusCode());
            objResp.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return objResp;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectResp getById(@PathParam("id") Long id ){
        ObjectResp objResp = new ObjectResp();
        try {
            objResp.setCode(Response.Status.OK.getStatusCode());
            objResp.setMessage(Response.Status.OK.getReasonPhrase());
            objResp.setData(repo.findById(id));

        }catch (Exception e){
            objResp.setCode(404);
            objResp.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return objResp;
    }

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    public ObjectResp create(Mmahasiswa obj){
        ObjectResp objResp = new ObjectResp();

        try {
            obj.setMahasiswapk(null);
            repo.persist(obj);
            if(repo.isPersistent(obj)){
                objResp.setCode(Response.Status.CREATED.getStatusCode());
                objResp.setMessage(lblmsg_save);
                objResp.setData(obj);
            }else {
                objResp.setCode(Response.Status.NOT_FOUND.getStatusCode());
                objResp.setMessage(lblmsg_empty);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return objResp;
    }

    @PUT
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    public ObjectResp update(@PathParam("id") Long id, Mmahasiswa obj){
        ObjectResp objResp = new ObjectResp();
        try {
            repo.getEntityManager().merge(obj);
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

    @DELETE
    @Path("/{id}")
    @Transactional
    public ObjectResp delete(@PathParam("id") Long id){
        ObjectResp objResp = new ObjectResp();
        try {
            repo.deleteById(id);
            objResp.setCode(Response.Status.OK.getStatusCode());
            objResp.setMessage(Response.Status.OK.getReasonPhrase());

        }catch (Exception e){
            e.printStackTrace();
            objResp.setCode(Response.Status.BAD_REQUEST.getStatusCode());
            objResp.setMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        }
        return objResp;
    }


}
