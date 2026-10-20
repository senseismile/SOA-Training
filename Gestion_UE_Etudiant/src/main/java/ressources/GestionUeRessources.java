package ressources;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Path("UE")
public class GestionUeRessources {

    public static UniteEnseignementBusiness uniteEnseignementMetier = new UniteEnseignementBusiness();

    @POST
    @Consumes("application/json")
    public Response addUniteEnseignement(UniteEnseignement ue) {
        if(uniteEnseignementMetier.addUniteEnseignement(ue))
            return Response.status(Response.Status.CREATED).build();
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @GET
    @Produces("application/json")
    public Response getUnitesEnseignement(@QueryParam("domaine") String domaine) {
        List<UniteEnseignement> liste = new ArrayList<>();

        if(domaine != null) {
            liste = uniteEnseignementMetier.getUEByDomaine(domaine);
        } else {
            liste = uniteEnseignementMetier.getListeUE();
        }

        if(liste.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(liste).build();
    }

    @PUT
    @Path("/{code}")
    @Consumes("application/json")
    public Response updateUE(UniteEnseignement updatedUE, @PathParam("code") int code) {
        if(uniteEnseignementMetier.updateUniteEnseignement(code, updatedUE)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{code}")
    public Response deleteUniteEnseignement(@PathParam("code") int code) {
        if(uniteEnseignementMetier.deleteUniteEnseignement(code))
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{code}")
    @Produces("application/json")
    public Response getUniteEnseignementById(@PathParam("code") int code) {
        UniteEnseignement ue = uniteEnseignementMetier.getUEByCode(code);
        if(ue != null)
            return Response.status(Response.Status.OK).entity(ue).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
