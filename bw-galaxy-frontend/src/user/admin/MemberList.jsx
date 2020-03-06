import React, { Component } from "react";
import { fetchAllUsers } from "../../util/APIUtils";
import { Role, formatDateTime } from "../../util/Helpers";
import LoadingIndicator from "../../common/LoadingIndicator";
import { notification } from "antd";
import { Link } from "react-router-dom";

export class MemberList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			users: [],
			loading: false
		};
		notification.config({
			placement: "topRight",
			bottom: 70,
			duration: 3
		});
	}

	componentDidMount() {
		this.mount = true;
		this.setState({ loading: true });
		fetchAllUsers()
			.then(response => {
				if (this.mount) {
					const us = response.result
						.filter(u => u.roles[0].name !== Role.Admin)
						.reduce((total, curr, index) => {
							let { id, name, username, email, createdAt } = curr;
							total[index] = {
								id,
								name,
								username,
								email,
								createdAt
							};
							return total;
						}, []);

					this.setState({ users: us, loading: false });
				}
			})
			.catch(err => {
				this.setState({ loading: false });
				notification.error({
					message: "BW Galaxy TDMS",
					description: "Error fetching user: Network issues."
				});
			});
	}

	componentWillUnmount() {
		this.mount = false;
	}

	render() {
		if (this.state.loading) {
			return <LoadingIndicator />;
		}
		return (
			<div className="container-fluid mt-3">
				<div className="row">
					<div className="col-12">
						<Link
							to="/admin/register"
							className="btn btn-sm btn-danger mr-1"
						>
							Add User
						</Link>

						<Link
							to="/admin/assignTask"
							className="btn btn-success btn-sm float-right"
						>
							Assign Task
						</Link>
					</div>
				</div>
				<div className="row">
					<div className="col-md-12">
						<table
							className="table  table-responsive-sm table-bordered mt-2"
							style={{
								borderWidth: 0,
								borderRadius: "10px",
								boxShadow: "0 0 5px rgba(0, 0, 0, 0.1)"
							}}
						>
							<thead>
								<tr>
									<th>ID</th>
									<th>Name</th>
									<th>Username</th>
									<th>Email</th>
									<th>Date Added</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								{this.state.users.map((user, i) => (
									<tr key={user.id}>
										<td>{i + 1}</td>
										<td>{user.name}</td>
										<td>@{user.username}</td>
										<td>{user.email}</td>
										<td>
											{formatDateTime(user.createdAt)}
										</td>

										<td>
											<Link
												to={`/admin/member/${user.id}`}
												className="btn btn-sm btn-outline-info mx-1"
											>
												View Member
											</Link>
										</td>
									</tr>
								))}
							</tbody>
						</table>
					</div>
				</div>
			</div>
		);
	}
}
