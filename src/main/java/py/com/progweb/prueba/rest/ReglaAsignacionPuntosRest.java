package py.com.progweb.prueba.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import py.com.progweb.prueba.ejb.ReglaAsignacionPuntosDAO;
import py.com.progweb.prueba.model.ReglaAsignacionPuntos;

@Path("reglas-asignaciones")
public class ReglaAsignacionPuntosRest {

    @Inject
    private ReglaAsignacionPuntosDAO reglaAsignacionPuntosBean;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        return Response.ok(reglaAsignacionPuntosBean.listar()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(ReglaAsignacionPuntos entity) {
        this.reglaAsignacionPuntosBean.agregar(entity);
        return Response.ok().build();
    }

    @GET
    @Path("/get-puntos/{monto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntos(@PathParam("monto") Double monto) {
        return Response.ok(reglaAsignacionPuntosBean.getCantidadDePuntos(monto)).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(ReglaAsignacionPuntos entity) throws Exception {
        this.reglaAsignacionPuntosBean.actualizar(entity);
        return Response.ok().build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@QueryParam("id") Integer id) {
        this.reglaAsignacionPuntosBean.eliminar(id);
        return Response.ok().build();
    }

}
