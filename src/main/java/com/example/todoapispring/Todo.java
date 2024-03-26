package com.example.todoapispring;

public class Todo{
	private Long id;
	private Boolean completed;
	private String title;
	private Integer userId;

	public Todo(long id, boolean completed, String title, int userId) {
		this.id = id;
		this.completed = completed;
		this.title = title;
		this.userId = userId;
	}

	public void setId(long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setCompleted(boolean completed){
		this.completed = completed;
	}

	public Boolean isCompleted(){
		return completed;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"Todo{" + 
			"id = '" + id + '\'' + 
			",completed = '" + completed + '\'' + 
			",title = '" + title + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}
