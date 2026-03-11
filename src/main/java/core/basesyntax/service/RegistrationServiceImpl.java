package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.exception.InvalidRegisterArgumentException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    public static final int MINIMUM_LOGIN_LENGTH = 6;
    public static final int MINIMUM_PASSWORD_LENGTH = 6;
    public static final int MINIMUM_AGE = 18;

    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new InvalidRegisterArgumentException("User can't be null");
        }

        if (storageDao.get(user.getLogin()) != null) {
            throw new InvalidRegisterArgumentException("User is already registered");
        }

        if (user.getLogin() == null || user.getLogin().length() < MINIMUM_LOGIN_LENGTH) {
            throw new InvalidRegisterArgumentException(
                    "Login does not exists or shorter than " + MINIMUM_LOGIN_LENGTH);
        }

        if (user.getPassword() == null || user.getPassword().length() < MINIMUM_PASSWORD_LENGTH) {
            throw new InvalidRegisterArgumentException(
                    "Password does not exists or shorter than " + MINIMUM_PASSWORD_LENGTH);
        }

        if (user.getAge() == null || user.getAge() < MINIMUM_AGE) {
            throw new InvalidRegisterArgumentException("You must be older than " + MINIMUM_AGE);
        }

        storageDao.add(user);
        return user;
    }
}
