package com.zzh.contest.filter;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class UsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {

    // @Override
    // protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
    //         throws AuthenticationException {
    //     prepareTimingAttackProtection();
    //     try {
    //         UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
    //         if (loadedUser == null) {
    //             throw new InternalAuthenticationServiceException(
    //                     "UserDetailsService returned null, which is an interface contract violation");
    //         }
    //         return loadedUser;
    //     } catch (UsernameNotFoundException ex) {
    //         mitigateAgainstTimingAttack(authentication);
    //         throw ex;
    //     } catch (InternalAuthenticationServiceException ex) {
    //         throw ex;
    //     } catch (Exception ex) {
    //         throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
    //     }
    // }
}
