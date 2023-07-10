package Workspace.io.BaseDemo.WebSecurity.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Workspace.io.BaseDemo.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JwtUserDetails implements UserDetails {

	public Long id;
	private String username;
	private String password;
   private Collection<?extends GrantedAuthority> authorities;

    public static JwtUserDetails creater(User user) {
    List <GrantedAuthority>	 authoritiesList=new ArrayList<>();
    authoritiesList.add(new SimpleGrantedAuthority("user"));
    return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassWord(), authoritiesList);	
    	
    }
   
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


	

}
