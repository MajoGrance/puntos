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
import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.model.BolsaPuntos;

@Path("/bolsas")
public class BolsaPuntosRest {

    @Inject
    private BolsaPuntosDAO bolsaPuntosBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        return Response.ok(bolsaPuntosBean.listar()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(BolsaPuntos entity) throws Exception {
        this.bolsaPuntosBean.agregar(entity);
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(BolsaPuntos entity) throws Exception {
        this.bolsaPuntosBean.actualizar(entity);
        return Response.ok().build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@QueryParam("id") Integer id) {
        this.bolsaPuntosBean.eliminar(id);
        return Response.ok().build();
    }
    
    @GET
    @Path("/byCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarByCliente(@QueryParam("id") Integer idCliente) {
        return Response.ok(bolsaPuntosBean.listarByCliente(idCliente)).build();
    }
    
    @GET
    @Path("/byRango")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarByCliente(
            @QueryParam("valorInicio") Integer valorInicio,
            @QueryParam("valorFin") Integer valorFin) {
        return Response.ok(bolsaPuntosBean.listarByRango(valorInicio, valorFin)).build();
    }
    
    @GET
    @Path("/vencidos/clientes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarClienteByPuntosAVencer(@QueryParam("dias") Integer dias){
        return Response.ok(bolsaPuntosBean.listarClienteByPuntosAVencer(dias)).build();
    }
    
    @POST
    @Path("/cargar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cargaPuntos(@PathParam("id") Integer idCliente, @QueryParam("monto") Double monto){
        this.bolsaPuntosBean.cargarPuntos(idCliente, monto);
        return Response.ok().build();
    }
    
}
