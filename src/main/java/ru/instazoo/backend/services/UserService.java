package ru.instazoo.backend.services;

import ru.instazoo.backend.entities.User;
import ru.instazoo.backend.payload.request.SingUpRequest;

public interface UserService {
    User createUser(SingUpRequest request);

}
