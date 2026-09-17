package com.resourceallocator.backend.config;

import com.resourceallocator.backend.application.port.out.AssignmentRepository;
import com.resourceallocator.backend.application.port.out.EmployeeRepository;
import com.resourceallocator.backend.application.port.out.ProjectRepository;
import com.resourceallocator.backend.application.port.out.TechnologyRepository;
import com.resourceallocator.backend.domain.model.Assignment;
import com.resourceallocator.backend.domain.model.AssignmentMode;
import com.resourceallocator.backend.domain.model.Employee;
import com.resourceallocator.backend.domain.model.EmployeeTechnology;
import com.resourceallocator.backend.domain.model.ProficiencyLevel;
import com.resourceallocator.backend.domain.model.Project;
import com.resourceallocator.backend.domain.model.ProjectStatus;
import com.resourceallocator.backend.domain.model.ProjectTechnologyRequirement;
import com.resourceallocator.backend.domain.model.Technology;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TechnologyRepository technologyRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final AssignmentRepository assignmentRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (!technologyRepository.findAll().isEmpty()) {
            return;
        }
        Map<String, Long> technologyIds = seedTechnologies();
        List<Long> employeeIds = seedEmployees(technologyIds);
        List<Long> projectIds = seedProjects(technologyIds);
        seedAssignments(employeeIds, projectIds);
    }

    private Map<String, Long> seedTechnologies() {
        Map<String, Long> ids = new LinkedHashMap<>();
        saveTechnology(ids, "Angular", "Frontend", "21", "Framework de aplicaciones web de Google.");
        saveTechnology(ids, "React", "Frontend", "19", "Librer\u00eda de interfaces de usuario de Meta.");
        saveTechnology(ids, "Vue", "Frontend", "3.5", "Framework progresivo para interfaces de usuario.");
        saveTechnology(ids, "TypeScript", "Lenguaje", "5.9", "Superset tipado de JavaScript.");
        saveTechnology(ids, "Node.js", "Backend", "22", "Entorno de ejecuci\u00f3n de JavaScript.");
        saveTechnology(ids, ".NET", "Backend", "9", "Framework de Microsoft para desarrollo de software.");
        saveTechnology(ids, "Java", "Backend", "21", "Lenguaje de programaci\u00f3n orientado a objetos.");
        saveTechnology(ids, "Python", "Backend", "3.13", "Lenguaje de programaci\u00f3n multiprop\u00f3sito.");
        saveTechnology(ids, "SQL Server", "Base de datos", "2022", "Motor de base de datos relacional de Microsoft.");
        saveTechnology(ids, "PostgreSQL", "Base de datos", "17", "Sistema de base de datos relacional de c\u00f3digo abierto.");
        saveTechnology(ids, "Docker", "DevOps", "27", "Plataforma de contenedores.");
        saveTechnology(ids, "Azure", "DevOps", "2024", "Plataforma de computaci\u00f3n en la nube de Microsoft.");
        return ids;
    }

    private void saveTechnology(Map<String, Long> ids, String name, String category,
                                String version, String description) {
        Technology saved = technologyRepository.save(
                new Technology(null, name, category, version, description));
        ids.put(name, saved.getId());
    }

    private List<Long> seedEmployees(Map<String, Long> tech) {
        List<Long> ids = new ArrayList<>();
        ids.add(seedEmployee(tech, "Mar\u00eda", "G\u00f3mez", "maria.gomez@empresa.com", "Desarrolladora Frontend", -2200,
                new String[][]{
                        {"Angular", "EXPERTO", "21", "6"},
                        {"TypeScript", "EXPERTO", "5.9", "6"},
                        {"Vue", "AVANZADO", "3.5", "3"}}));
        ids.add(seedEmployee(tech, "Carlos", "Ruiz", "carlos.ruiz@empresa.com", "Desarrollador Backend", -1800,
                new String[][]{
                        {"Node.js", "EXPERTO", "22", "7"},
                        {"PostgreSQL", "AVANZADO", "17", "4"},
                        {"TypeScript", "AVANZADO", "5.9", "5"}}));
        ids.add(seedEmployee(tech, "Ana", "L\u00f3pez", "ana.lopez@empresa.com", "Ingeniera de Software", -1200,
                new String[][]{
                        {"Java", "AVANZADO", "21", "4"},
                        {"Python", "AVANZADO", "3.13", "3"},
                        {"SQL Server", "MEDIO", "2022", "2"}}));
        ids.add(seedEmployee(tech, "Luis", "Mart\u00ednez", "luis.martinez@empresa.com", "Desarrollador Full Stack", -900,
                new String[][]{
                        {"Angular", "AVANZADO", "21", "3"},
                        {"Node.js", "AVANZADO", "22", "4"},
                        {"PostgreSQL", "MEDIO", "17", "2"},
                        {"TypeScript", "AVANZADO", "5.9", "4"}}));
        ids.add(seedEmployee(tech, "Elena", "S\u00e1nchez", "elena.sanchez@empresa.com", "Especialista en Bases de Datos", -2000,
                new String[][]{
                        {"SQL Server", "EXPERTO", "2022", "8"},
                        {"PostgreSQL", "EXPERTO", "17", "7"}}));
        ids.add(seedEmployee(tech, "Jorge", "D\u00edaz", "jorge.diaz@empresa.com", "Desarrolladora Frontend", -700,
                new String[][]{
                        {"React", "AVANZADO", "19", "3"},
                        {"TypeScript", "MEDIO", "5.9", "2"}}));
        ids.add(seedEmployee(tech, "Laura", "Fern\u00e1ndez", "laura.fernandez@empresa.com", "DevOps Engineer", -1500,
                new String[][]{
                        {"Docker", "EXPERTO", "27", "6"},
                        {"Azure", "AVANZADO", "2024", "4"},
                        {"Node.js", "MEDIO", "22", "3"}}));
        ids.add(seedEmployee(tech, "Pedro", "Moreno", "pedro.moreno@empresa.com", "Desarrollador .NET", -1100,
                new String[][]{
                        {".NET", "EXPERTO", "9", "6"},
                        {"SQL Server", "AVANZADO", "2022", "4"},
                        {"TypeScript", "MEDIO", "5.9", "2"}}));
        ids.add(seedEmployee(tech, "Sof\u00eda", "Ortega", "sofia.ortega@empresa.com", "Desarrolladora Java", -1300,
                new String[][]{
                        {"Java", "EXPERTO", "21", "7"},
                        {"PostgreSQL", "MEDIO", "17", "2"},
                        {"Docker", "MEDIO", "27", "1"}}));
        ids.add(seedEmployee(tech, "Miguel", "Torres", "miguel.torres@empresa.com", "Cient\u00edfico de Datos", -600,
                new String[][]{
                        {"Python", "EXPERTO", "3.13", "5"},
                        {"PostgreSQL", "AVANZADO", "17", "3"}}));
        ids.add(seedEmployee(tech, "Carmen", "Navarro", "carmen.navarro@empresa.com", "Desarrolladora React", -500,
                new String[][]{
                        {"React", "EXPERTO", "19", "4"},
                        {"TypeScript", "AVANZADO", "5.9", "4"},
                        {"Node.js", "MEDIO", "22", "2"}}));
        ids.add(seedEmployee(tech, "Rafael", "Castro", "rafael.castro@empresa.com", "Arquitecto de Software", -3000,
                new String[][]{
                        {".NET", "EXPERTO", "9", "10"},
                        {"Java", "AVANZADO", "21", "6"},
                        {"Python", "AVANZADO", "3.13", "5"},
                        {"Azure", "AVANZADO", "2024", "4"}}));
        ids.add(seedEmployee(tech, "Luc\u00eda", "Ramos", "lucia.ramos@empresa.com", "Desarrolladora Backend", -800,
                new String[][]{
                        {"Node.js", "AVANZADO", "22", "3"},
                        {"PostgreSQL", "AVANZADO", "17", "3"}}));
        ids.add(seedEmployee(tech, "Andr\u00e9s", "Su\u00e1rez", "andres.suarez@empresa.com", "Desarrollador Frontend", -400,
                new String[][]{
                        {"Vue", "AVANZADO", "3.5", "2"},
                        {"TypeScript", "MEDIO", "5.9", "2"}}));
        return ids;
    }

    private long seedEmployee(Map<String, Long> tech, String firstName, String lastName, String email,
                              String position, int hireDaysAgo, String[][] techs) {
        int maxYears = 0;
        for (String[] t : techs) {
            maxYears = Math.max(maxYears, Integer.parseInt(t[3]));
        }

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPosition(position);
        employee.setHireDate(LocalDate.now().plusDays(hireDaysAgo));
        employee.setCostPerDay(BigDecimal.valueOf(180L + (long) maxYears * 40));

        for (String[] t : techs) {
            employee.addTechnology(new EmployeeTechnology(
                    null,
                    tech.get(t[0]),
                    ProficiencyLevel.fromLabel(t[1]),
                    t[2],
                    Integer.parseInt(t[3])));
        }
        return employeeRepository.save(employee).getId();
    }

    private List<Long> seedProjects(Map<String, Long> tech) {
        List<Long> ids = new ArrayList<>();
        ids.add(seedProject(tech, "Portal Bancario", "Portal web transaccional para clientes de banca digital.",
                "Banco Nacional", "ACTIVO", -200, 60, 260,
                new String[][]{
                        {"Angular", "AVANZADO", "2", "21"},
                        {"TypeScript", "MEDIO", "1", "5.9"},
                        {"SQL Server", "MEDIO", "2", "2022"}}));
        ids.add(seedProject(tech, "App de Comercio M\u00f3vil", "Aplicaci\u00f3n m\u00f3vil y backend para tienda en l\u00ednea.",
                "RetailMax", "ACTIVO", -90, 25, 280,
                new String[][]{
                        {"React", "AVANZADO", "2", "19"},
                        {"Node.js", "AVANZADO", "2", "22"}}));
        ids.add(seedProject(tech, "ERP Corporativo", "Migraci\u00f3n y modernizaci\u00f3n de ERP interno.",
                "Consultores Integrales", "ACTIVO", -60, 150, 320,
                new String[][]{
                        {".NET", "EXPERTO", "4", "9"},
                        {"SQL Server", "AVANZADO", "3", "2022"}}));
        ids.add(seedProject(tech, "Plataforma de Datos", "Construcci\u00f3n de un data warehouse anal\u00edtico.",
                "Telecom Global", "ACTIVO", -140, 12, 300,
                new String[][]{
                        {"Python", "AVANZADO", "3", "3.13"},
                        {"PostgreSQL", "AVANZADO", "2", "17"}}));
        ids.add(seedProject(tech, "Sistema de Pagos", "Pasarela de pagos de alta disponibilidad.",
                "Fintech Solutions", "EN_PLANIFICACION", 20, 200, 340,
                new String[][]{
                        {"Java", "EXPERTO", "5", "21"},
                        {"PostgreSQL", "AVANZADO", "3", "17"},
                        {"Docker", "MEDIO", "1", "27"}}));
        ids.add(seedProject(tech, "Portal de Recursos Humanos", "Intranet para gesti\u00f3n de personal.",
                "Grupo Andino", "ACTIVO", -30, 40, 240,
                new String[][]{
                        {"Vue", "AVANZADO", "1", "3.5"},
                        {"Node.js", "MEDIO", "1", "22"}}));
        ids.add(seedProject(tech, "Migraci\u00f3n a la Nube", "Migraci\u00f3n de infraestructura a Azure.",
                "Seguros Vida", "ACTIVO", -45, 18, 300,
                new String[][]{
                        {"Azure", "AVANZADO", "3", "2024"},
                        {"Docker", "AVANZADO", "2", "27"}}));
        ids.add(seedProject(tech, "API de Log\u00edstica", "APIs de seguimiento y despacho de paquetes.",
                "LogiExpress", "EN_PLANIFICACION", 10, 120, 260,
                new String[][]{
                        {"Node.js", "AVANZADO", "2", "22"},
                        {"PostgreSQL", "MEDIO", "1", "17"},
                        {"TypeScript", "MEDIO", "1", "5.9"}}));
        return ids;
    }

    private long seedProject(Map<String, Long> tech, String name, String description, String client,
                             String status, int startDaysAgo, int endDaysFromNow, int dailyRate,
                             String[][] requirements) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setClient(client);
        project.setStatus(ProjectStatus.fromLabel(status));
        project.setStartDate(LocalDate.now().plusDays(startDaysAgo));
        project.setEndDate(LocalDate.now().plusDays(endDaysFromNow));
        project.setDailyRate(BigDecimal.valueOf(dailyRate));

        for (String[] req : requirements) {
            project.addRequirement(new ProjectTechnologyRequirement(
                    null,
                    tech.get(req[0]),
                    ProficiencyLevel.fromLabel(req[1]),
                    Integer.parseInt(req[2]),
                    req[3],
                    null));
        }
        return projectRepository.save(project).getId();
    }

    private void seedAssignments(List<Long> employees, List<Long> projects) {
        seedAssignment(employees, projects, 0, 0, AssignmentMode.HORAS, 8, -200, 8,
                "Asignada al m\u00f3dulo de transacciones.");
        seedAssignment(employees, projects, 10, 1, AssignmentMode.HORAS, 6, -90, 12,
                "Frontend del carrito.");
        seedAssignment(employees, projects, 7, 2, AssignmentMode.RANGO, null, -60, 150,
                "L\u00edder t\u00e9cnico .NET.");
        seedAssignment(employees, projects, 9, 3, AssignmentMode.RANGO, null, -140, 10,
                "Modelado del datamart.");
        seedAssignment(employees, projects, 4, 3, AssignmentMode.RANGO, null, -140, 10,
                "Dise\u00f1o del esquema.");
        seedAssignment(employees, projects, 6, 6, AssignmentMode.HORAS, 4, -45, 15,
                "Automatizaci\u00f3n de despliegues.");
        seedAssignment(employees, projects, 13, 5, AssignmentMode.HORAS, 7, -30, 40,
                "Interfaz de la intranet.");
        seedAssignment(employees, projects, 12, 1, AssignmentMode.HORAS, 5, -90, 25,
                "APIs de pedidos.");
        seedAssignment(employees, projects, 8, 4, AssignmentMode.RANGO, null, -120, -40,
                "Asignaci\u00f3n previa a planificaci\u00f3n.");
        seedAssignment(employees, projects, 2, 2, AssignmentMode.RANGO, null, -60, -10,
                "Soporte durante UAT.");
        seedAssignment(employees, projects, 11, 4, AssignmentMode.RANGO, null, -45, 60,
                "Definici\u00f3n de arquitectura.");
        seedAssignment(employees, projects, 1, 7, AssignmentMode.HORAS, 8, 2, 92,
                "Desarrollo de APIs (programada).");
        seedAssignment(employees, projects, 3, 0, AssignmentMode.HORAS, 8, -200, -15,
                "Finalizada.");
    }

    private void seedAssignment(List<Long> employees, List<Long> projects, int employeeIndex,
                                int projectIndex, AssignmentMode mode, Integer hoursPerDay,
                                int startDaysAgo, int endDaysFromNow, String notes) {
        Assignment assignment = new Assignment();
        assignment.setEmployeeId(employees.get(employeeIndex));
        assignment.setProjectId(projects.get(projectIndex));
        assignment.setMode(mode);
        assignment.setHoursPerDay(hoursPerDay);
        assignment.setStartDate(LocalDate.now().plusDays(startDaysAgo));
        assignment.setEndDate(LocalDate.now().plusDays(endDaysFromNow));
        assignment.setNotes(notes);
        assignmentRepository.save(assignment);
    }
}