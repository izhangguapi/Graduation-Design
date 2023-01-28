package com.zzh.contest.controller;

import com.zzh.contest.entity.Role;
import com.zzh.contest.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    final RoleService service;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    public List<Role> getAll() {
        return service.list();
    }

    @GetMapping("/user")
    public List<Role> getUserRole() {
        return service.getUserRole(1);
    }
}
