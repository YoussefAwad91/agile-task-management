public class AuthService {

    UserDAO userDao= new UserDAO(); //? created userdao object

    public boolean register(User user){ //todo: create user object
        if(validateUsernameUnique(user.getUsername())){
            userDao.addUser(user);
            return true;
        }
        return false;
    }

    public User login(String username, String password){ //null if user not found or password incorrect
        User u = userDao.findByUsername(username);
        if (u==null){
            return null;
        }
        if (PasswordHasher.verify(password, u.getPassword())){ //getPassword is hashed password
            return u;
        }
        return null;
    }

    private boolean validateUsernameUnique(String username){
        if (userDao.findByUsername(username)==null){
            return true;
        }
        return false;
    }
}
