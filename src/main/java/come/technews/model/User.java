package come.technews.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String userName;
	@Column(unique = true)
	private String email;
	private String password;
	@Transient
	boolean loggedIn;
	

	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> posts;
	// Need to use FetchType.LAZY to resolve multiple bags exception
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Vote> votes;
	// Need to use FetchType.LAZY to resolve multiple bags exception
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments;
	
	public User() {
		
	}
	
	public User(Integer id, String userName, String email, String password) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUsername(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public List<Vote> getVotes() {
		return votes;
	}
	
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof User)) return false;
		User user = (User) o;
		
		return isLoggedIn() == user.isLoggedIn() &&
				Objects.equals(getId(), user.getId()) &&
				Objects.equals(getUserName(), user.getUserName()) &&
				Objects.equals(getEmail(), user.getEmail()) &&
				Objects.equals(getPassword(), user.getPassword()) &&
				Objects.equals(getPosts(), user.getPosts()) &&
				Objects.equals(getVotes(), user.getVotes()) && 
				Objects.equals(getComments(), user.getComments());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUserName(), getEmail(), getPassword(), isLoggedIn(), getPosts(), getVotes(), getComments());
	}
	
	@Override
	public String toString() {
		return "User{" + 
			   "id=" + id +
			   ", username=" + userName + '\'' + 
			   ", email=" + email + '\'' + 
			   ", password=" + password + '\'' + 
			   ", loggedIn=" + loggedIn + 
			   ", posts=" + posts + 
			   ", votes=" + votes + 
			   ", comments=" + comments + 
			   '}';
	}
}
