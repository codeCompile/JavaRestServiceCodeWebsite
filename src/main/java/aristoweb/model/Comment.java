package aristoweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the comments database table.
 * 
 */
@Entity
@Table(name="comments")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String comment_ID;

	@Column(name="comment_approved")
	private byte commentApproved;

	@Lob
	@Column(name="comment_author")
	private String commentAuthor;

	@Column(name="comment_author_email")
	private String commentAuthorEmail;

	private String comment_author_IP;

	@Lob
	@Column(name="comment_content")
	private String commentContent;

	@Column(name="comment_count")
	private BigInteger commentCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="comment_date")
	private Date commentDate;

	@Column(name="comment_like_count")
	private int commentLikeCount;

	private BigInteger comment_post_ID;

	public Comment() {
	}

	public String getComment_ID() {
		return this.comment_ID;
	}

	public void setComment_ID(String comment_ID) {
		this.comment_ID = comment_ID;
	}

	public byte getCommentApproved() {
		return this.commentApproved;
	}

	public void setCommentApproved(byte commentApproved) {
		this.commentApproved = commentApproved;
	}

	public String getCommentAuthor() {
		return this.commentAuthor;
	}

	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	public String getCommentAuthorEmail() {
		return this.commentAuthorEmail;
	}

	public void setCommentAuthorEmail(String commentAuthorEmail) {
		this.commentAuthorEmail = commentAuthorEmail;
	}

	public String getComment_author_IP() {
		return this.comment_author_IP;
	}

	public void setComment_author_IP(String comment_author_IP) {
		this.comment_author_IP = comment_author_IP;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public BigInteger getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(BigInteger commentCount) {
		this.commentCount = commentCount;
	}

	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public int getCommentLikeCount() {
		return this.commentLikeCount;
	}

	public void setCommentLikeCount(int commentLikeCount) {
		this.commentLikeCount = commentLikeCount;
	}

	public BigInteger getComment_post_ID() {
		return this.comment_post_ID;
	}

	public void setComment_post_ID(BigInteger comment_post_ID) {
		this.comment_post_ID = comment_post_ID;
	}

}