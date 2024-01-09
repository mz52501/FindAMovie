import {useState, useEffect} from "react";
import axios from "axios";
import {Table} from "reactstrap";
import NavBarAdmin from "./NavBarAdmin";

function Admin() {
    const [users, setUsers] = useState([]);

    const fetchUsers = () => {
        axios.get("http://localhost:8080/admin").then(res => {
            setUsers(res.data);
        });
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    return(
        <div>
            <NavBarAdmin />
            <h2>Users</h2>
            <Table style={{width: "1000px"}}
            >
                <thead>
                <tr>
                    <th>
                        #
                    </th>
                    <th>
                        Username
                    </th>
                    <th>
                        Email
                    </th>
                </tr>
                </thead>
                <tbody>
                {users.map((user, index) => (
                    <tr>
                        <th>
                            {index + 1}
                        </th>
                        <th>
                            {user.username}
                        </th>
                        <th>
                            {user.email}
                        </th>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
}

export default Admin;