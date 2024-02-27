import React,{useState} from "react";
import RoleSlider from "./RoleSlider";
import PasswordCheckList from "react-password-checklist";
import { API } from "../api";
import "./Register.css";

import img1 from '../img/ante.png';
import img2 from '../img/ante_zoom.png';
import img3 from '../img/ante_obrnut.png';
import img4 from '../img/ante_inverzni.png';


export const Register = (props) => {

    const images = [
        {
            image: img1,
            role: 'EMPLOYEE',
            name: 'Employee'
        },
        {
            image: img2,
            role: 'REVISER',
            name: 'Reviser'
        },
        {
            image: img3,
            role: 'ACCOUNTANT_RECEIPT',
            name: 'Accountant (receipts)'
        },
        {
            image: img3,
            role: 'ACCOUNTANT_OFFER',
            name: 'Accountant (offers)'
        },
        {
            image: img3,
            role: 'ACCOUNTANT_INT_DOC',
            name: 'Accountant (internal documents)'
        },
        {
            image: img4,
            role: 'DIRECTOR',
            name: 'Director'
        }
    ];

    const [email,  setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [firstname, setFirstName] = useState('');
    const [lastname, setLastName] = useState('');
    const [role, setRole] = useState('EMPLOYEE')

    async function handleSubmit(e) {
        e.preventDefault();
        const formData = new FormData(e.target);
        const formJSON = Object.fromEntries(formData.entries());
        formJSON.role = role
        //console.log(formJSON);
        try {
            await API.post("/api/v1/authenticate/register", formJSON, {
                headers: { "Content-Type": "application/json" },
            });
            alert("Registracija uspješna");
        }
        catch (err) {
            alert(err.message || "Error during registration");
        }
    }

    return (
        <div className="form-container">

            <RoleSlider images={images} onChangeRole={setRole}/>

            <form className="register-form" onSubmit={handleSubmit}>
                <label htmlFor="firstName">First name: </label>
                <input value={firstname} onChange={(e) => setFirstName(e.target.value)} name="firstName" id="firstName" placeholder="first name" required/>

                <label htmlFor="lastName">Last name: </label>
                <input value={lastname} onChange={(e) => setLastName(e.target.value)} name="lastName" id="lastName" placeholder="last Name" required/>

                <label htmlFor="email">Email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="youremail@gmail.com" id="email" name="email" required/>

                <label htmlFor="password">Password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="*********" id="password" name="password" required/>

                <PasswordCheckList className="password-checklist"
                    rules={["minLength", "specialChar", "number", "capital"]}
                    minLength={6}
                    value={pass}
                    onChange={(isValid) => {}}
                />

                <button type="submit">Register</button>
            </form>
            <button onClick={ () => props.onFormSwitch('login')}>Already have an account? Login here.</button>
        </div>
    )
}