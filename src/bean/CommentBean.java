package bean;

public class CommentBean {
	private long id;
	private String comment;
	private String upvotes;
	private String downvotes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(String upvotes) {
		this.upvotes = upvotes;
	}

	public String getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(String downvotes) {
		this.downvotes = downvotes;
	}

}
