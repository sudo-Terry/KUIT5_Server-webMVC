package controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static final RequestMapper instance = new RequestMapper();

    private final Map<String, Controller> mappings = new HashMap<>();

    private RequestMapper() {
        initMapping();
    }

    public static RequestMapper getInstance() {
        return instance;
    }

    private void initMapping() {
        mappings.put("/", new HomeController());
        mappings.put("/user/signup", new CreateUserController());
        mappings.put("/user/userList", new ListUserController());
        mappings.put("/user/login", new LoginUserController());
        mappings.put("/user/logout", new LogoutUserController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/updateForm", new UpdateUserFormController());
    }

    public Controller getController(String path) {
        return mappings.get(path);
    }
}