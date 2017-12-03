package aristoweb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the posts database table.
 * 
 */
@Entity
@Table(name="posts")
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="post_ID")
	private Long post_ID;

	@Lob
	@Column(name="article_content")
	private String articleContent;

	@Lob
	@Column(name="article_title")
	private String articleTitle;

	@Column(name="post_comment_count")
	private int postCommentCount;

	@Lob
	@Column(name="post_content")
	private String postContent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="post_date")
	private Date postDate;

	@Column(name="post_has_article")
	private byte postHasArticle;

	@Column(name="post_like_count")
	private int postLikeCount;

	@Column(name="post_status")
	private String postStatus;

	@Column(name="post_type")
	private String postType;

	public Post() {
	}

	public Long getPost_ID() {
		return this.post_ID;
	}

	public void setPost_ID(Long post_ID) {
		this.post_ID = post_ID;
	}

	public String getArticleContent() {
		return this.articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public int getPostCommentCount() {
		return this.postCommentCount;
	}

	public void setPostCommentCount(int postCommentCount) {
		this.postCommentCount = postCommentCount;
	}

	public String getPostContent() {
		return this.postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public byte getPostHasArticle() {
		return this.postHasArticle;
	}

	public void setPostHasArticle(byte postHasArticle) {
		this.postHasArticle = postHasArticle;
	}

	public int getPostLikeCount() {
		return this.postLikeCount;
	}

	public void setPostLikeCount(int postLikeCount) {
		this.postLikeCount = postLikeCount;
	}

	public String getPostStatus() {
		return this.postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	public String getPostType() {
		return this.postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

}