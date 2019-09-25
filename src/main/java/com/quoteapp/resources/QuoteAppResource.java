package com.quoteapp.resources;

import com.codahale.metrics.annotation.Timed;
import com.quoteapp.api.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

@Path("/quotes")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class QuoteAppResource {
    private static List<Quote> quotes = new ArrayList<Quote>();

    public QuoteAppResource(List<Quote> quotes) {
        this.quotes = quotes;
    }
    
    @GET
    @Timed
    @Path("random/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Quote findRandomXML() {
        Random randomGenerator = new Random();
        int id = randomGenerator.nextInt(quotes.size());
        return quotes.get(id);
    }
    
    @GET
    @Timed
    @Path("random")
    @Produces(MediaType.APPLICATION_JSON)
    public Quote findRandom() {
        Random randomGenerator = new Random();
        int id = randomGenerator.nextInt(quotes.size());
        return quotes.get(id);
    }
    
    @GET
    @Timed
    @Path("xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Quote> findAllXML() {
        return quotes;
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public List<Quote> findAll() {
        return quotes;
    }
    
    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Quote create(Quote quote) {
        quotes.add(quote.getId(), quote);
        return quote;
    }
    
    @PUT
    @Timed
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") int id, Quote quote) {
        quotes.set(id, quote);
    }

    @DELETE
    @Timed
    @Path("{id}")
    public void remove(@PathParam("id") int id) {
        quotes.remove(id);
    }

    @GET
    @Timed
    @Path("{id}/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Quote findXML(@PathParam("id") int id) {
        return quotes.get(id);
    }
    
    @GET
    @Timed
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Quote find(@PathParam("id") int id) {
        return quotes.get(id);
    }
}
