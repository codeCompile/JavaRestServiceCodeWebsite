package aristoweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the likes_counter database table.
 * 
 */
@Entity
@Table(name="likes_counter")
@NamedQuery(name="LikesCounter.findAll", query="SELECT l FROM LikesCounter l")
public class LikesCounter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String like_ID;

	private BigInteger like_content_ID;

	private String like_IP;

	@Column(name="like_type")
	private String likeType;

	public LikesCounter() {
	}

	public String getLike_ID() {
		return this.like_ID;
	}

	public void setLike_ID(String like_ID) {
		this.like_ID = like_ID;
	}

	public BigInteger getLike_content_ID() {
		return this.like_content_ID;
	}

	public void setLike_content_ID(BigInteger like_content_ID) {
		this.like_content_ID = like_content_ID;
	}

	public String getLike_IP() {
		return this.like_IP;
	}

	public void setLike_IP(String like_IP) {
		this.like_IP = like_IP;
	}

	public String getLikeType() {
		return this.likeType;
	}

	public void setLikeType(String likeType) {
		this.likeType = likeType;
	}

}