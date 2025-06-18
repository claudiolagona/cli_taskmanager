package com.cli_taskmanager.manager;

import com.cli_taskmanager.core.Project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectManagerTest {
    private ProjectManager manager;
    private Project mockProject;

    @BeforeEach
    void setUp() {
        manager = ProjectManagerImplementation.getInstance();
        manager.clearAllProjects();

        mockProject = mock(Project.class);
        when(mockProject.getId()).thenReturn("proj1");
    }

    @Test
    void testAddAndGetProject() {
        manager.addProject(mockProject);
        Optional<Project> retrieved = manager.getProjectById("proj1");

        assertTrue(retrieved.isPresent());
        assertEquals("proj1", retrieved.get().getId());
    }

    @Test
    void testRemoveProject() {
        manager.addProject(mockProject);
        manager.removeProject("proj1");

        assertFalse(manager.getProjectById("proj1").isPresent());
    }

    @Test
    void testClearAllProjects() {
        manager.addProject(mockProject);
        manager.clearAllProjects();

        assertEquals(0, manager.getAllProjects().size());
    }

    @Test
    void testExecuteProject() {
        manager.addProject(mockProject);
        manager.executeProject("proj1");

        verify(mockProject).execute();
    }

    @Test
    void testExecuteNonExistingProject() {
        Exception e = assertThrows(RuntimeException.class, () -> {
            manager.executeProject("nonexistent");
        });
        assertTrue(e.getMessage().contains("nonexistent"));
    }
}
