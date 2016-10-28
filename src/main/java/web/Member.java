package web;
import javax.persistence.*;

@Entity @Table(name="member")
public class Member {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String email;
	String password;
	String name;
	public String getName() { return name; }
}
