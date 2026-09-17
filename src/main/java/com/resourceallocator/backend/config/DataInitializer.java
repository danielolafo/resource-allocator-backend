package com.resourceallocator.backend.config;

import com.resourceallocator.backend.entity.*;
import com.resourceallocator.backend.repository.EmployeeRepository;
import com.resourceallocator.backend.repository.ProjectRepository;
import com.resourceallocator.backend.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TechnologyRepository technologyRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (technologyRepository.count() > 0) {
            return;
        }
        seedTechnologies();
        seedEmployees();
        seedProjects();
    }

    private void seedTechnologies() {
        saveTech("Angular", "Frontend", "21", "Framework de aplicaciones web de Google.");
        saveTech("React", "Frontend", "19", "Librer\u00eda de interfaces de usuario de Meta.");
        saveTech("Vue", "Frontend", "3.5", "Framework progresivo para interfaces de usuario.");
        saveTech("TypeScript", "Lenguaje", "5.9", "Superset tipado de JavaScript.");
        saveTech("Node.js", "Backend", "22", "Entorno de ejecuci\u00f3n de JavaScript.");
        saveTech(".NET", "Backend", "9", "Framework de Microsoft para desarrollo de software.");
        saveTech("Java", "Backend", "21", "Lenguaje de programaci\u00f3n orientado a objetos.");
        saveTech("Python", "Backend", "3.13", "Lenguaje de programaci\u00f3n multiprop\u00f3sito.");
        saveTech("SQL Server", "Base de datos", "2022", "Motor de base de datos relacional de Microsoft.");
        saveTech("PostgreSQL", "Base de datos", "17", "Sistema de base de datos relacional de c\u00f3digo abierto.");
        saveTech("Docker", "DevOps", "27", "Plataforma de contenedores.");
        saveTech("Azure", "DevOps", "2024", "Plataforma de computaci\u00f3n en la nube de Microsoft.");
    }

    private void saveTech(String name, String category, String version, String description) {
        technologyRepository.save(new Technology(name, category, version, description));
    }

    private void seedEmployees() {
        seedEmployee("Mar\u00eda", "G\u00f3mez", "maria.gomez@empresa.com", "Desarrolladora Frontend", -2200, 180,
                new String[][]{
                        {"Angular", "EXPERTO", "21", "6"},
                        {"TypeScript", "EXPERTO", "5.9", "6"},
                        {"Vue", "AVANZADO", "3.5", "3"}});
        seedEmployee("Carlos", "Ruiz", "carlos.ruiz@empresa.com", "Desarrollador Backend", -1800, 220,
                new String[][]{
                        {"Node.js", "EXPERTO", "22", "7"},
                        {"PostgreSQL", "AVANZADO", "17", "4"},
                        {"TypeScript", "AVANZADO", "5.9", "5"}});
        seedEmployee("Ana", "L\u00f3pez", "ana.lopez@empresa.com", "Ingeniera de Software", -1200, 180,
                new String[][]{
                        {"Java", "AVANZADO", "21", "4"},
                        {"Python", "AVANZADO", "3.13", "3"},
                        {"SQL Server", "MEDIO", "2022", "2"}});
        seedEmployee("Luis", "Mart\u00ednez", "luis.martinez@empresa.com", "Desarrollador Full Stack", -900, 220,
                new String[][]{
                        {"Angular", "AVANZADO", "21", "3"},
                        {"Node.js", "AVANZADO", "22", "4"},
                        {"PostgreSQL", "MEDIO", "17", "2"},
                        {"TypeScript", "AVANZADO", "5.9", "4"}});
        seedEmployee("Elena", "S\u00e1nchez", "elena.sanchez@empresa.com", "Especialista en Bases de Datos", -2000, 300,
                new String[][]{
                        {"SQL Server", "EXPERTO", "2022", "8"},
                        {"PostgreSQL", "EXPERTO", "17", "7"}});
        seedEmployee("Jorge", "D\u00edaz", "jorge.diaz@empresa.com", "Desarrolladora Frontend", -700, 260,
                new String[][]{
                        {"React", "AVANZADO", "19", "3"},
                        {"TypeScript", "MEDIO", "5.9", "2"}});
        seedEmployee("Laura", "Fern\u00e1ndez", "laura.fernandez@empresa.com", "DevOps Engineer", -1500, 260,
                new String[][]{
                        {"Docker", "EXPERTO", "27", "6"},
                        {"Azure", "AVANZADO", "2024", "4"},
                        {"Node.js", "MEDIO", "22", "3"}});
        seedEmployee("Pedro", "Moreno", "pedro.moreno@empresa.com", "Desarrollador .NET", -1100, 260,
                new String[][]{
                        {".NET", "EXPERTO", "9", "6"},
                        {"SQL Server", "AVANZADO", "2022", "4"},
                        {"TypeScript", "MEDIO", "5.9", "2"}});
        seedEmployee("Sof\u00eda", "Ortega", "sofia.ortega@empresa.com", "Desarrolladora Java", -1300, 300,
                new String[][]{
                        {"Java", "EXPERTO", "21", "7"},
                        {"PostgreSQL", "MEDIO", "17", "2"},
                        {"Docker", "MEDIO", "27", "1"}});
        seedEmployee("Miguel", "Torres", "miguel.torres@empresa.com", "Cient\u00edfico de Datos", -600, 260,
                new String[][]{
                        {"Python", "EXPERTO", "3.13", "5"},
                        {"PostgreSQL", "AVANZADO", "17", "3"}});
        seedEmployee("Carmen", "Navarro", "carmen.navarro@empresa.com", "Desarrolladora React", -500, 220,
                new String[][]{
                        {"React", "EXPERTO", "19", "4"},
                        {"TypeScript", "AVANZADO", "5.9", "4"},
                        {"Node.js", "MEDIO", "22", "2"}});
        seedEmployee("Rafael", "Castro", "rafael.castro@empresa.com", "Arquitecto de Software", -3000, 400,
                new String[][]{
                        {".NET", "EXPERTO", "9", "10"},
                        {"Java", "AVANZADO", "21", "6"},
                        {"Python", "AVANZADO", "3.13", "5"},
                        {"Azure", "AVANZADO", "2024", "4"}});
        seedEmployee("Luc\u00eda", "Ramos", "lucia.ramos@empresa.com", "Desarrolladora Backend", -800, 220,
                new String[][]{
                        {"Node.js", "AVANZADO", "22", "3"},
                        {"PostgreSQL", "AVANZADO", "17", "3"}});
        seedEmployee("Andr\u00e9s", "Su\u00e1rez", "andres.suarez@empresa.com", "Desarrollador Frontend", -400, 220,
                new String[][]{
                        {"Vue", "AVANZADO", "3.5", "2"},
                        {"TypeScript", "MEDIO", "5.9", "2"}});
    }

    private void seedEmployee(String firstName, String lastName, String email, String position,
                              int hireDaysAgo, int baseCost, String[][] techs) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPosition(position);
        employee.setHireDate(LocalDate.now().plusDays(hireDaysAgo));

        int totalYears = 0;
        for (String[] tech : techs) {
            totalYears = Math.max(totalYears, Integer.parseInt(tech[3]));
        }
        employee.setCostPerDay(BigDecimal.valueOf(180L + (long) totalYears * 40));

        for (String[] tech : techs) {
            Technology technology = technologyRepository.findByNameIgnoreCase(tech[0]).orElseThrow();
            EmployeeTechnology et = new EmployeeTechnology(
                    technology,
                    ProficiencyLevel.fromLabel(tech[1]),
                    tech[2],
                    Integer.parseInt(tech[3]));
            employee.addTechnology(et);
        }
        employeeRepository.save(employee);
    }

    private void seedProjects() {
        seedProject("Portal Bancario", "Portal web transaccional para clientes de banca digital.",
                "Banco Nacional", "ACTIVO", -200, 60, 260,
                new String[][]{
                        {"Angular", "AVANZADO", "2", null},
                        {"TypeScript", "MEDIO", "1", null},
                        {"SQL Server", "MEDIO", "2", null}});
        seedProject("App de Comercio M\u00f3vil", "Aplicaci\u00f3n m\u00f3vil y backend para tienda en l\u00ednea.",
                "RetailMax", "ACTIVO", -90, 25, 280,
                new String[][]{
                        {"React", "AVANZADO", "2", null},
                        {"Node.js", "AVANZADO", "2", null}});
        seedProject("ERP Corporativo", "Migraci\u00f3n y modernizaci\u00f3n de ERP interno.",
                "Consultores Integrales", "ACTIVO", -60, 150, 320,
                new String[][]{
                        {".NET", "EXPERTO", "4", null},
                        {"SQL Server", "AVANZADO", "3", null}});
        seedProject("Plataforma de Datos", "Construcci\u00f3n de un data warehouse anal\u00edtico.",
                "Telecom Global", "ACTIVO", -140, 12, 300,
                new String[][]{
                        {"Python", "AVANZADO", "3", null},
                        {"PostgreSQL", "AVANZADO", "2", null}});
        seedProject("Sistema de Pagos", "Pasarela de pagos de alta disponibilidad.",
                "Fintech Solutions", "EN_PLANIFICACION", 20, 200, 340,
                new String[][]{
                        {"Java", "EXPERTO", "5", null},
                        {"PostgreSQL", "AVANZADO", "3", null},
                        {"Docker", "MEDIO", "1", null}});
        seedProject("Portal de Recursos Humanos", "Intranet para gesti\u00f3n de personal.",
                "Grupo Andino", "ACTIVO", -30, 40, 240,
                new String[][]{
                        {"Vue", "AVANZADO", "1", null},
                        {"Node.js", "MEDIO", "1", null}});
        seedProject("Migraci\u00f3n a la Nube", "Migraci\u00f3n de infraestructura a Azure.",
                "Seguros Vida", "ACTIVO", -45, 18, 300,
                new String[][]{
                        {"Azure", "AVANZADO", "3", null},
                        {"Docker", "AVANZADO", "2", null}});
        seedProject("API de Log\u00edstica", "APIs de seguimiento y despacho de paquetes.",
                "LogiExpress", "EN_PLANIFICACION", 10, 120, 260,
                new String[][]{
                        {"Node.js", "AVANZADO", "2", null},
                        {"PostgreSQL", "MEDIO", "1", null},
                        {"TypeScript", "MEDIO", "1", null}});
    }

    private void seedProject(String name, String description, String client, String status,
                             int startDaysAgo, int endDaysFromNow, int dailyRate, String[][] requirements) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setClient(client);
        project.setStatus(ProjectStatus.fromLabel(status));
        project.setStartDate(LocalDate.now().plusDays(startDaysAgo));
        project.setEndDate(LocalDate.now().plusDays(endDaysFromNow));
        project.setDailyRate(BigDecimal.valueOf(dailyRate));

        for (String[] req : requirements) {
            Technology technology = technologyRepository.findByNameIgnoreCase(req[0]).orElseThrow();
            project.addRequirement(new ProjectTechnologyRequirement(
                    technology,
                    ProficiencyLevel.fromLabel(req[1]),
                    Integer.parseInt(req[2]),
                    technology.getVersion(),
                    null));
        }
        projectRepository.save(project);
    }
}