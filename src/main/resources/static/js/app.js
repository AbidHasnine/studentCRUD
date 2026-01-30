const API_URL = '/students';


document.addEventListener('DOMContentLoaded', function() {
    loadStudents();
    document.getElementById('studentForm').addEventListener('submit', handleSubmit);
    document.getElementById('cancelBtn').addEventListener('click', resetForm);
});

//load students
async function loadStudents() {
    try {
        const response = await fetch(API_URL);
        const students = await response.json();
        displayStudents(students);
    } catch (error) {
        console.error('Error loading students:', error);
    }
}

//display students in table
function displayStudents(students) {
    const tbody = document.getElementById('studentsTableBody');
    const emptyState = document.getElementById('emptyState');
    
    tbody.innerHTML = '';
    
    if (students.length === 0) {
        emptyState.style.display = 'block';
        return;
    }
    
    emptyState.style.display = 'none';
    
    students.forEach(student => {
        const row = `
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.email}</td>
                <td>${student.department}</td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="editStudent(${student.id})">Update</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteStudent(${student.id})">Delete</button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

//input submission
async function handleSubmit(e) {
    e.preventDefault();
    
    const id = document.getElementById('studentId').value;
    const student = {
        name: document.getElementById('studentName').value,
        email: document.getElementById('studentEmail').value,
        department: document.getElementById('studentDepartment').value
    };
    
    try {
        if (id) {
            await fetch(`${API_URL}/${id}`, {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(student)
            });
        } else {
            await fetch(API_URL, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(student)
            });
        }
        resetForm();
        loadStudents();
    } catch (error) {
        console.error('Error saving student:', error);
    }
}

//edit
async function editStudent(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const student = await response.json();
        
        document.getElementById('studentId').value = student.id;
        document.getElementById('studentName').value = student.name;
        document.getElementById('studentEmail').value = student.email;
        document.getElementById('studentDepartment').value = student.department;
        document.getElementById('formTitle').textContent = 'Edit Student';
        document.getElementById('submitBtn').textContent = 'Update';
        document.getElementById('cancelBtn').style.display = 'inline-block';
    } catch (error) {
        console.error('Error loading student:', error);
    }
}

//delete
async function deleteStudent(id) {
    if (!confirm('Delete this student?')) return;
    
    try {
        await fetch(`${API_URL}/${id}`, {method: 'DELETE'});
        loadStudents();
    } catch (error) {
        console.error('Error deleting student:', error);
    }
}

//reset
function resetForm() {
    document.getElementById('studentForm').reset();
    document.getElementById('studentId').value = '';
    document.getElementById('formTitle').textContent = 'Add New Student';
    document.getElementById('submitBtn').textContent = 'Save';
    document.getElementById('cancelBtn').style.display = 'none';
}

