package web;
import javax.persistence.*;

@Entity @Table(name="member")
public class Member {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	String email;
	String password;
	String name;
	public String getName() { return name; }
}
