# 🖥️ App-Digitalizacija

**App-Digitalizacija** is a repository for the project assignment on the subject **Software Engineering** at the **Faculty of Electrical Engineering and Computing**. The application focuses on **Optical Character Recognition (OCR)**, allowing users to efficiently extract text from uploaded images.

---

## 🌐 Deployed Application

The application is live and accessible via this link:  
🔗 [App-Digitalizacija on Render](https://kompletici-front.onrender.com)

---

## 📖 Project Overview

The goal of this application is to provide users with a simple and fast solution for **Optical Character Recognition (OCR)** on images they upload. Once an image is uploaded, the application processes it and returns the text extracted from the image.

---

## 👤 User Roles

The application includes multiple user roles, each with different privileges for viewing and managing data. Below are the roles and their permissions:

- **Director**:
  - View activity statistics for all users.
  - Review the entire history of scanned documents.
  - Delete employee accounts.
  - Sign scanned documents.

- **Auditor**:
  - Review documents scanned by employees.
  - Change the category of documents.
  - Forward certain documents to the accountant.

- **Accountant**:
  - Send documents to the director for signing.
  - Archive documents.

- **Employee**:
  - Scan documents.
  - Send scanned documents to the auditor.
  - View personal scan history.
  - Authenticate and register accounts.

---

## 🔑 Authentication and Registration

The app starts with **Login** and **Register** pages:
- **Login**: For users with existing accounts.
- **Register**: For new users to create an account.

---

## 🛠️ Running the Application Locally

To run the app locally, follow these steps:

1. **Database Setup in PGAdmin**:
   - Initialize the database in PGAdmin according to the provided SQL dump settings.

2. **Backend Setup in IntelliJ**:
   - Import the backend project into IntelliJ.
   - Configure the database settings in the application according to your local environment.
   - Run the backend application.

3. **Frontend Setup with React**:
   - Import the frontend project into your development environment.
   - Open the terminal and navigate to the frontend directory.
   - Run the frontend application using:
     ```bash
     npm start
     ```

Once these steps are completed, the application will be available locally, and users can access OCR functionality, manage documents, and interact with the system based on their assigned roles.

---

## 🔧 Code Tracking and Control

For version control and collaboration, **Git** and **GitHub** are used. All changes and updates are tracked via the GitHub repository.

---

## 🌍 Deployment and Hosting

The application is deployed using **Render**, ensuring a stable and fast user experience while interacting with the OCR features.

---

## 📄 Documentation

All project documentation is written using **LaTeX** for clarity, organization, and ease of maintenance. Relevant documents and instructions are available in the respective LaTeX files.

---

**App-Digitalizacija** provides an efficient and user-friendly way to manage documents through OCR, streamlining workflows for different roles within the company.
