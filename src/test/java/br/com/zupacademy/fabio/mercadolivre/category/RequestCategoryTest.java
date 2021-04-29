package br.com.zupacademy.fabio.mercadolivre.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RequestCategoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    private void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenRequestCategory_WhenMotherCategoryIsNull_ThenMustCreateCategory(){
        String name = "Television";
        RequestCategory requestCategory = new RequestCategory(name, null);
        Category category = requestCategory.convertToCategory(categoryRepository);

        Mockito.verify(categoryRepository, Mockito.never()).findById(Mockito.anyLong());
        assertEquals(name, category.getName());
        assertNull(category.getMother_category());
    }

    @Test
    void givenRequestCategory_WhenMotherCategoryIsValid_ThenMustCreateCategory() {
        String name = "Television";
        RequestCategory requestCategory = new RequestCategory(name, 1L);

        Category motherCategory = new Category("Technology");

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(motherCategory));
        Category category = requestCategory.convertToCategory(categoryRepository);
        assertEquals(name, category.getName());
        assertEquals(motherCategory, category.getMother_category());
    }
}