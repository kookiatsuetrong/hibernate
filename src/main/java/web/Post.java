package web;
import javax.persistence.*;

@Entity @Table(name="post")
public class Post {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	String topic;
	String detail;
	String photo;
	
	public long getId()       { return id;     }
	public String getTopic()  { return topic;  }
	public String getDetail() { return detail; }
	public String getPhoto()  { return photo;  }
}
