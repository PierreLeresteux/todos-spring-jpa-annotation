package fr.pierre.todos.resource;

import com.sun.jersey.spi.resource.Singleton;
import fr.pierre.todos.model.Todo;
import fr.pierre.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Path("/todo")
@Consumes({APPLICATION_JSON})
@Produces({APPLICATION_JSON + ";charset=utf-8"})
@Singleton
@Component
public class TodoResource {

	@Autowired
	TodoService service;

	@Context
	UriInfo uriInfo;

	@GET
	public Response get() {
		return Response.ok(new GenericEntity<List<Todo>>(service.getTodos()) {
		}).build();
	}

	@POST
	@Consumes(APPLICATION_JSON)
	public Response insertTodo(Todo todo) {
		todo = service.create(todo);
		URI location = URI.create(uriInfo.getAbsolutePath() + "/" + todo.getId());
		return Response.created(location).entity(todo).build();
	}

	@DELETE
	@Path("/{id:[0-9]}")
	public Response removeTodo(@PathParam("id") final Long id) {
		service.delete(id);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9]}")
	public Response getTodo(@PathParam("id") final Long id) {
		Todo todo = service.getTodo(id);
		return Response.ok().entity(todo).build();
	}

	@PUT
	@Consumes(APPLICATION_JSON)
	@Path("/{id:[0-9]}")
	public Response updateTodo(@PathParam("id") final Long id, Todo todo) {
		Todo mergedTodo = service.updateTodo(id, todo);
		return Response.ok().entity(mergedTodo).build();
	}

}
