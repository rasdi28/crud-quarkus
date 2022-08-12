package com.sdd.resources;


import com.sdd.domain.Macademic;
import com.sdd.model.ObjectResp;
import com.sdd.repo.MacademicRepo;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/macademic")
public class MacademicResource {

    @Inject
    MacademicRepo repo;

    @ConfigProperty(name = "label.msg.id.notfound")
    String lblmsg_empty;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectResp create(Macademic obj){
        ObjectResp objResp = new ObjectResp();

        try {
            obj.setMacademicpk(null);
            repo.persist(obj);
            if(repo.isPersistent(obj)){
                objResp.setCode(Response.Status.OK.getStatusCode());
                objResp.setMessage(Response.Status.OK.getReasonPhrase());
                objResp.setData(obj);
            }else {
                objResp.setCode(Response.Status.NOT_FOUND.getStatusCode());
                objResp.setMessage(lblmsg_empty);
            }

        }catch (Exception e){

        }

        return objResp;
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{muserfk}")
    public ObjectResp getByUser(@PathParam("muserfk") Integer muserfk){
        ObjectResp objResp = new ObjectResp();
        try {

            objResp.setCode(Response.Status.OK.getStatusCode());
            objResp.setMessage(Response.Status.OK.getReasonPhrase());
            objResp.setData(repo.list("muserfk", Sort.by("macademicpk desc"), muserfk));

        }catch (Exception e){
            e.printStackTrace();
            objResp.setCode(Response.Status.BAD_REQUEST.getStatusCode());
            objResp.setMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        }

        return objResp;
    }


}
