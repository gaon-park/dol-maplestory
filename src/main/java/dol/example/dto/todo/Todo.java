package dol.example.dto.todo;

import dol.example.common.info.TodoInfo;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class Todo implements Serializable {
    private TodoInfo todoInfo;
    private Symbol symbolDTO;

    public Todo(TodoInfo todoInfo){
        this.todoInfo = todoInfo;
    }

    public Todo(TodoInfo todoInfo, Symbol symbolDTO){
        if(isTodoOfSymbol(todoInfo)){
            this.todoInfo = todoInfo;
            this.symbolDTO = symbolDTO;
        }
    }

    public void setSymbolDTO(Symbol symbolDTO){
        if(isTodoOfSymbol(todoInfo)){
            this.symbolDTO = symbolDTO;
        }
    }

    /**
     * 아케인 심볼, 어센틱 심볼 관련한 메할일만 SymbolDTO로 생성 가능
     * @return
     */
    private boolean isTodoOfSymbol(TodoInfo todoInfo){
        if(!TodoInfo.getTodoInfoListForArcaneSymbol().contains(todoInfo)
                || !TodoInfo.getTodoInfoListForAuthenticSymbol().contains(todoInfo)){
            return false;
        }
        return true;
    }
}
