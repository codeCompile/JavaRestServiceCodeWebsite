package aristoweb.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the authors database table.
 * 
 */
@Entity
@Table(name="authors")
@NamedQuery(name="Author.findAll", query="SELECT a FROM Author a")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String author_ID;

	@Lob
	@Column(name="author_avatar")
	private String authorAvatar;

	@Lob
	@Column(name="author_description")
	private String authorDescription;

	@Column(name="author_name")
	private String authorName;

	public Author() {
	}

	public String getAuthor_ID() {
		return this.author_ID;
	}

	public void setAuthor_ID(String author_ID) {
		this.author_ID = author_ID;
	}

	public String getAuthorAvatar() {
		return this.authorAvatar;
	}

	public void setAuthorAvatar(String authorAvatar) {
		this.authorAvatar = authorAvatar;
	}

	public String getAuthorDescription() {
		return this.authorDescription;
	}

	public void setAuthorDescription(String authorDescription) {
		this.authorDescription = authorDescription;
	}

	public String getAuthorName() {
		return this.authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}