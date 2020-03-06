/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { Layout, Menu, Dropdown, Icon } from "antd";
import { Role } from "./../util/Helpers";
import "./AppHeader.css";
import logo from "../logo.png";

const Header = Layout.Header;

class AppHeader extends Component {
	constructor(props) {
		super(props);
		this.handleMenuClick = this.handleMenuClick.bind(this);
	}

	handleMenuClick({ key }) {
		if (key === "logout") {
			this.props.onLogout();
		}
	}

	render() {
		let menuItems;
		if (this.props.currentUser) {
			const { role } = this.props.currentUser;
			if (role === Role.Admin) {
				menuItems = [
					<Menu.Item key="/">
						<Link to="/" title="Home">
							<Icon type="home" className="nav-icon" />
						</Link>
					</Menu.Item>,
					<Menu.Item key="/admin/members">
						<Link to="/admin/members" title="Members">
							<Icon type="team" className="nav-icon" />
						</Link>
					</Menu.Item>,
					<Menu.Item key="/admin/tasks">
						<Link to="/admin/tasks" title="Tasks">
							<Icon type="schedule" className="nav-icon" />
						</Link>
					</Menu.Item>,
					<Menu.Item key="/admin/register">
						<Link to="/admin/register" title="Register Member">
							<Icon type="plus-circle-o" className="nav-icon" />
						</Link>
					</Menu.Item>,
					<Menu.Item key="/profile" className="profile-menu">
						<ProfileDropdownMenu
							currentUser={this.props.currentUser}
							handleMenuClick={this.handleMenuClick}
						/>
					</Menu.Item>
				];
			} else {
				menuItems = [
					<Menu.Item key="/member">
						<Link to="/member" title="Home">
							<Icon type="home" className="nav-icon" />
						</Link>
					</Menu.Item>,
					<Menu.Item key="/member/tasklist">
						<Link to="/member/tasklist" title="Tasks">
							<Icon type="schedule" className="nav-icon" />
						</Link>
					</Menu.Item>,
					<Menu.Item key="/profile" className="profile-menu">
						<ProfileDropdownMenu
							currentUser={this.props.currentUser}
							handleMenuClick={this.handleMenuClick}
						/>
					</Menu.Item>
				];
			}
		} else {
			menuItems = [
				<Menu.Item key="/login">
					<Link to="/login">Login</Link>
				</Menu.Item>
			];
		}

		return (
			<Header className="app-header">
				<div className="container">
					<div className="app-title">
						<Link to="/" className="nav-icon">
							<img
								src={logo}
								alt="BW Galaxy TDMS"
								className="logo"
							/>
						</Link>
					</div>
					<Menu
						className="app-menu"
						mode="horizontal"
						selectedKeys={[this.props.location.pathname]}
						style={{ lineHeight: "64px" }}
					>
						{menuItems}
					</Menu>
				</div>
			</Header>
		);
	}
}

function ProfileDropdownMenu(props) {
	const dropdownMenu = (
		<Menu onClick={props.handleMenuClick} className="profile-dropdown-menu">
			<Menu.Item key="user-info" className="dropdown-item" disabled>
				<div className="user-full-name-info">
					{props.currentUser.name}
				</div>
				<div className="username-info">
					@{props.currentUser.username}
				</div>
			</Menu.Item>
			<Menu.Divider />
			<Menu.Item key="logout" className="dropdown-item">
				Logout
			</Menu.Item>
		</Menu>
	);

	return (
		<Dropdown
			overlay={dropdownMenu}
			trigger={["click"]}
			getPopupContainer={() =>
				document.getElementsByClassName("profile-menu")[0]
			}
		>
			<a className="ant-dropdown-link">
				<Icon
					type="user"
					className="nav-icon"
					style={{ marginRight: 0 }}
				/>{" "}
				<Icon type="down" />
			</a>
		</Dropdown>
	);
}

export default withRouter(AppHeader);