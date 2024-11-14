package fr.eiffel.sio.todolist.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String className, Object identifier) {
        super("Can't find the ressource of type "+className+" with identifier "+identifier.toString());
    }

}
