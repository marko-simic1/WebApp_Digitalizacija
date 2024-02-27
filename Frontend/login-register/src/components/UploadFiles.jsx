import React, { useState, useEffect, useRef } from 'react';
import { API } from '../api';
import './UploadFiles.css';
import ImageDocument from './ImageDocument';

const UploadFiles = () => {

    const initialized = useRef(false);

    useEffect(() => {
        if (!initialized.current) {
            initialized.current = true;
            getDocs();
        }
    }, []);

    const userinfo = JSON.parse(sessionStorage.getItem("user"));
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [returned, setReturned] = useState(false);
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(false);

    //let data = undefined;

    const config = {
        headers: {
            Authorization: "Bearer " + userinfo.accessToken,
            "Access-Control-Allow-Origin": "*",
        },
    };

    const handleFileChange = (e) => {
        const files = e.target.files;
        console.log(files);

        setSelectedFiles(files);
    };

    async function handleSubmit(e) {
        e.preventDefault();
        setLoading(true);
        const formData = new FormData();
        //console.log(selectedFiles);
        for (const file of selectedFiles){
            formData.append('files', file);
        };
        
        for (const value of formData.values()) {
            console.log(value);
        };

        try {
            const res = await API.post('/api/v1/images/upload', formData, config);
            setData(prevArray => [...prevArray, ...res.data]);
            setReturned(true);
            setLoading(false);
            console.log(data);
        } catch(err) {
            console.log(err);
        }
    };

    async function getDocs() {
        try {
            const res = await API.get('/api/v1/document/all-unconfirmed', config);
            setData(prevArray => [...prevArray, ...res.data]);
            setReturned(true);
            console.log("here");
        } catch(err) {
            console.log(err);
        }
    }

    return (
        <div className='upload-container'>
        <form onSubmit={handleSubmit} className='upload-form'>
            <label htmlFor="imageUpload">Select Images:</label>
            <input
            type="file"
            id="imageUpload"
            name="images"
            accept="image/*"
            multiple
            onChange={handleFileChange}
            required/>
        <div className='submit-div'>
            <button type="submit">Submit</button>
            {
                loading && (
                    <div className='spinner'></div>
                )
            }
        </div>
        
        {returned && (
            <div className='uploaded-files'>
                { data.map((imgdoc, index) => (
                    <ImageDocument key={imgdoc.documentId} imgdoc={imgdoc}/>
                ))}
            </div>
        )}
      </form>
    </div>
  );
};

export default UploadFiles;
