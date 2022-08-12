package com.sdd.resources;

import com.sdd.domain.Muser;
import com.sdd.model.LoginReq;
import com.sdd.model.ObjectResp;
import com.sdd.model.UserChangePass;
import com.sdd.repo.MuserRepo;
import com.sdd.utils.SysUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/Muser")
public class MuserResource {

    

    @ConfigProperty(name = "label.msg.saved")
    String lblmsg_save;

    @ConfigProperty(name = "label.msg.id.notfound")
    String lblmsg_empty;

    @Inject
    MuserRepo repo;


    @Path("/login")
    @POST
    public ObjectResp login(LoginReq objReq){
        ObjectResp objResp = new ObjectResp();
        try {
            Muser oUser = repo.find("userid", objReq.getUserid()).firstResult();

            if(oUser !=null){
                if(oUser.getPassword().trim().equals(SysUtils.encryptionCommand(objReq.getPassword()))){
                    objResp.setCode(Response.Status.OK.getStatusCode());
                    objResp.setMessage(Response.Status.OK.getReasonPhrase());
                    objResp.setData(oUser);
                }else {
//                    objResp.setCode(Response.Status.EXPECTATION_FAILED.getStatusCode());
                    objResp.setMessage("invalid Password");
                }
            }else {
//                objResp.setCode(Response.Status.EXPECTATION_FAILED.getStatusCode());
                objResp.setMessage("invalid userid");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return objResp;

    }

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
    public ObjectResp findById(@PathParam("id") Long id){
        ObjectResp objResp = new ObjectResp();
        try {
            objResp.setCode(Response.Status.OK.getStatusCode());
            objResp.setMessage(Response.Status.OK.getReasonPhrase());
            objResp.setData(repo.findById(id));
        }catch (Exception e){
            e.printStackTrace();
            objResp.setCode(Response.Status.BAD_REQUEST.getStatusCode());
            objResp.setMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        }
        return objResp;
    }


    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    public ObjectResp create(Muser obj){
        ObjectResp objResp = new ObjectResp();
        try {
            obj.setMuserpk(null);
            obj.setPassword(SysUtils.encryptionCommand(obj.getPassword()));
            obj.setLastupdated(new Date());
            repo.persist(obj);
            if(repo.isPersistent(obj)){
                objResp.setCode(Response.Status.OK.getStatusCode());
                objResp.setMessage(Response.Status.OK.getReasonPhrase());
                objResp.setData(obj);
            }else {
                objResp.setCode(Response.Status.BAD_REQUEST.getStatusCode());
                objResp.setMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
            }
        }catch (Exception e){
            e.printStackTrace();
            objResp.setCode(Response.Status.BAD_REQUEST.getStatusCode());
            objResp.setMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        }

        return objResp;
    }

    @PUT
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    public ObjectResp update(Muser obj){
        ObjectResp objResp = new ObjectResp();

        try {
            obj.setPassword(SysUtils.encryptionCommand(obj.getPassword()));
            repo.getEntityManager().merge(obj);
            objResp.setCode(Response.Status.OK.getStatusCode());
            objResp.setMessage(Response.Status.OK.getReasonPhrase());
            objResp.setData(obj);
        }catch (Exception e){
            e.printStackTrace();
        }

        return objResp;
    }

    @POST
    @Path("/changepass")
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    public ObjectResp changepass(UserChangePass obj){
        ObjectResp objResp = new ObjectResp();

        try {
            Muser user = repo.findById(obj.muserpk);
            if(user !=null){
                String password = SysUtils.encryptionCommand(obj.password.trim());

                if(user.getPassword().trim().equals(password)){
                    user.setPassword(SysUtils.encryptionCommand(obj.newpassword.trim()));
                    user.setLastupdated(new Date());
                    repo.getEntityManager().merge(user);
                    objResp.setCode(Response.Status.OK.getStatusCode());
                    objResp.setMessage("Password Has Updated");
                }else {
                    objResp.setCode(Response.Status.NOT_ACCEPTABLE.getStatusCode());
                    objResp.setMessage("invalid existing Password");
                }
            }else {
                objResp.setCode(Response.Status.NOT_ACCEPTABLE.getStatusCode());
                objResp.setMessage("User id not Found");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return objResp;
    }


    @DELETE
    @Transactional
    @Path("/{id}")
    public ObjectResp delete(@PathParam("id") Long id){
        ObjectResp objResp = new ObjectResp();
        try {
            Muser obj = repo.findById(id);
            if(obj !=null){
                repo.deleteById(id);
                objResp.setCode(Response.Status.OK.getStatusCode());
                objResp.setMessage("data berhasil dihapus");
                objResp.setData(obj);
            }else {
                objResp.setMessage("tidak ada data");
            }
        }catch (Exception e){

        }

        return objResp;
    }

}
