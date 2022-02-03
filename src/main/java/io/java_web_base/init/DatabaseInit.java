package io.java_web_base.init;

//@Component
//public class DatabaseInit implements InitializingBean {
//
//    private final UserServiceImpl userService;
//
//    public DatabaseInit(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public void afterPropertiesSet() {
//
//        UserForm userForm = UserForm.builder()
//                .username("admin")
//                .password("admin")
//                .roles(Arrays.asList("user", "admin"))
//                .email("admin@mail.be")
//                .build();
//
//        try {
//            this.userService.insert(userForm);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
