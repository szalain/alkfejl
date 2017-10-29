package hu.elte.alkfelj.dataman.dataman.Controller;

import hu.elte.alkfelj.dataman.dataman.Entity.Request.AddRoleRequest;
import hu.elte.alkfelj.dataman.dataman.Entity.Role;
import hu.elte.alkfelj.dataman.dataman.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


    @RestController
    @RequestMapping("/roles")
    public class RoleController {
        private RoleRepository roleRepository;

        @Autowired
        public RoleController(RoleRepository roleRepository) {
            this.roleRepository = roleRepository;
        }

        @RequestMapping(method = RequestMethod.GET)
        public List<Role> findAllRoles() {
            return roleRepository.findAll();
        }

        @RequestMapping(method = RequestMethod.POST)
        public void addRole(@RequestBody AddRoleRequest addRoleRequest) {
            Role role = new Role();
            role.setRoleName(addRoleRequest.getRoleName());
            role.setLevel(addRoleRequest.getLevel());
            roleRepository.save(role);
        }
    }
