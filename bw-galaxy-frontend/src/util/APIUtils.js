import { API_BASE_URL, ACCESS_TOKEN } from "../constants";

const request = options => {
	const headers = new Headers({
		"Content-Type": "application/json"
	});

	if (localStorage.getItem(ACCESS_TOKEN)) {
		headers.append(
			"Authorization",
			"Bearer " + localStorage.getItem(ACCESS_TOKEN)
		);
	}

	const defaults = { headers: headers };
	options = Object.assign({}, defaults, options);

	return fetch(options.url, options).then(response =>
		response.json().then(json => {
			if (!response.ok) {
				return Promise.reject(json);
			}
			return json;
		})
	);
};

/**
 *
 *  User Related Actions and Queries
 *
 */

export function assignTask({ userId, taskId }) {
	return request({
		url: `${API_BASE_URL}/assign/${userId}/${taskId}`,
		method: "PUT"
	});
}

export function fetchAllUsers() {
	return request({
		url: API_BASE_URL + "/users/all",
		method: "GET"
	});
}

export function fetchUserTask(userId, taskId) {
	return request({
		url: `${API_BASE_URL}/task/${userId}/${taskId}`,
		method: "GET"
	});
}

export function fetchUser(userId) {
	return request({
		url: `${API_BASE_URL}/user/${userId}`,
		method: "GET"
	});
}

// Fetching the user_id for task
export function fetchTaskUser(taskId) {
	return request({
		url: `${API_BASE_URL}/task/user/${taskId}`,
		method: "GET"
	});
}

// Fetch all task for a user
export function fetchUserTasks(userId) {
	return request({
		url: `${API_BASE_URL}/tasks/${userId}`,
		method: "GET"
	});
}

export function getUserProfile(username) {
	return request({
		url: API_BASE_URL + "/users/" + username,
		method: "GET"
	});
}

export function login(loginRequest) {
	return request({
		url: API_BASE_URL + "/auth/signin",
		method: "POST",
		body: JSON.stringify(loginRequest)
	});
}

export function signup(signupRequest) {
	return request({
		url: API_BASE_URL + "/auth/register",
		method: "POST",
		body: JSON.stringify(signupRequest)
	});
}

export function checkUsernameAvailability(username) {
	return request({
		url:
			API_BASE_URL +
			"/user/checkUsernameAvailability?username=" +
			username,
		method: "GET"
	});
}

export function checkEmailAvailability(email) {
	return request({
		url: API_BASE_URL + "/user/checkEmailAvailability?email=" + email,
		method: "GET"
	});
}

export function getCurrentUser() {
	if (!localStorage.getItem(ACCESS_TOKEN)) {
		return Promise.reject("No access token set.");
	}

	return request({
		url: API_BASE_URL + "/user/me",
		method: "GET"
	});
}

/**
 *
 *  Task Related Actions and Queries
 *
 */
export function fetchAllTasks() {
	return request({
		url: API_BASE_URL + "/task/all",
		method: "GET"
	});
}

export function fetchTask(taskId) {
	return request({
		url: `${API_BASE_URL}/task/${taskId}`,
		method: "GET"
	});
}

export function pendingTasks() {
	return request({
		url: `${API_BASE_URL}/task/pending`,
		method: "GET"
	});
}

export function completedTask() {
	return request({
		url: `${API_BASE_URL}/task/completed`,
		method: "GET"
	});
}

export function deleteTaskById(taskId) {
	return request({
		url: `${API_BASE_URL}/task/${taskId}`,
		method: "DELETE"
	});
}

export function createTask(taskRequest) {
	return request({
		url: `${API_BASE_URL}/task/create`,
		method: "POST",
		body: JSON.stringify(taskRequest)
	});
}

export function updateTask(taskId, taskRequest) {
	return request({
		url: `${API_BASE_URL}/task/${taskId}`,
		method: "PUT",
		body: JSON.stringify(taskRequest)
	});
}

export function taskCompleted(taskId) {
	return request({
		url: `${API_BASE_URL}/task/complete/${taskId}`,
		method: "PUT"
	});
}
