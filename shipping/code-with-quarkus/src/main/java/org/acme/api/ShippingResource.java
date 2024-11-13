package org.acme.api;
import org.acme.domain.Address;
import org.acme.domain.ShippingOrder;
import org.acme.domain.ShippingOrder.ShippingStatus;
import org.acme.service.ShippingService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/shipping")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShippingResource {

    @Inject
    ShippingService shippingService;

    @POST
    @Path("/order")
    public Response createShippingOrder(@QueryParam("orderId") Long orderId, @QueryParam("customerId") Long customerId) {
        try {
            ShippingOrder shippingOrder = shippingService.createShippingOrder(orderId, customerId);
            return Response.ok(shippingOrder).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/order/{orderId}/status")
    public Response updateShippingStatus(@PathParam("orderId") Long orderId, ShippingStatus status) {
        try {
            shippingService.updateShippingStatus(orderId, status);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/address")
    public Response addCustomerAddress(@QueryParam("customerId") Long customerId, @QueryParam("address") String address) {
        Address customerAddress = shippingService.addCustomerAddress(customerId, address);
        return Response.ok(customerAddress).build();
    }
}
